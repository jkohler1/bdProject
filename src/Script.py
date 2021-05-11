import mysql.connector
from mysql.connector import errorcode
import os
import csv
from datetime import datetime

def executeScriptFromFile(filename):
    fd = open(filename, 'r')
    sqlFile = fd.read()
    fd.close()

    for command in sqlFile.split(';'):
        conn.cursor().execute(command)


def importCsv(filename, delim):
    with open(filename) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=delim)
        list=[]
        column=[]
        count=0
        for row in csv_reader:
            if(count==0):
                column=row
                count=count+1
            else:
                dico={}
                for i in range(len(column)):
                    dico[column[i]]=row[i]
                list.append(dico)
    return list


def executeQuery(insert_stmt, data, cursor, conn):
    try:
        # Executing the SQL command
        cursor.execute(insert_stmt, data)
    except Exception as e:
    # Rolling back in case of error
        conn.rollback()
        print(insert_stmt)
        print(data)
        print(e)
        exit(1)


try:
    conn = mysql.connector.connect(user='bd_user', password='bd_password', host='localhost')

except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Something is wrong with your user name or password")
    elif err.errno == errorcode.ER_BAD_DB_ERROR:
        print("Database does not exist")
    else:
        print(err)

else:
    conn.cursor().execute("CREATE SCHEMA IF NOT EXISTS db_bd")
    conn.database = 'db_bd'
    executeScriptFromFile('CreateTable.sql')
    conn.commit()
    cursor = conn.cursor()
    directory = 'resources'
    dico={}
    iso_codes=[]

    for filename in os.listdir(directory):
        if(filename == "vaccinations.csv" or filename == "hospitals.csv"):
            dico[filename.split(".")[0]]=importCsv(directory+"/"+filename, ',')
        else:
            dico[filename.split(".")[0]]=importCsv(directory+"/"+filename, ';')


    for climat in dico["climate"]:
        insert_stmt = (
            """INSERT INTO Climats(id,description)
            VALUES (%s,%s)"""
        )
        data = (climat["id"],climat["decription"])
        executeQuery(insert_stmt,data,cursor,conn)
    regions={}
    continents={}

    for pays in dico["country"]:
        if not pays["continent"] in continents:
            insert_stmt = (
                """INSERT INTO Continents(nom)
                VALUES (%s)"""
            )
            data = [(pays["continent"])]
            executeQuery(insert_stmt,data,cursor,conn)
            continents[pays["continent"]]=cursor.lastrowid

        if not pays["region"] in regions:
            insert_stmt = (
                """INSERT INTO Regions(nom,continent_id)
                VALUES (%s,%s)"""
            )
            data = (pays["region"],continents[pays["continent"]])
            executeQuery(insert_stmt,data,cursor,conn)
            regions[pays["region"]]=cursor.lastrowid

        insert_stmt = (
            """INSERT INTO Pays(iso_code,nom,hdi,population,superficie,region_id,climat_id)
            VALUES (%s,%s,%s,%s,%s,%s,%s)"""
        )
        if len(pays["climate"]) == 0:
            pays["climate"] = None
        iso_codes.append(pays["iso_code"])
        data = (pays["iso_code"],pays["country"],pays["hdi"],pays["population"],pays["area_sq_ml"],regions[pays["region"]],pays["climate"])
        executeQuery(insert_stmt,data,cursor,conn)

    for test in dico["vaccinations"]:
        insert_stmt = (
            """INSERT INTO Tests(iso_code,date,tests,vaccinations)
            VALUES (%s,%s,%s,%s)"""
        )
        if(len(test["tests"]) == 0):
            test["tests"] = 0
        if(len(test["vaccinations"]) == 0):
            test["vaccinations"] = 0

        if test["iso_code"] in iso_codes:
            data = (test["iso_code"],datetime.strptime(test["date"][:19], "%Y-%m-%d %H:%M:%S"),test["tests"],test["vaccinations"])
            executeQuery(insert_stmt,data,cursor,conn)

    for vaccin in dico["producers"]:
        insert_stmt = (
            """INSERT INTO Vaccins(iso_code,date,vaccins)
            VALUES (%s,%s,%s)"""
        )
        if vaccin["iso_code"] in iso_codes:
            data = (vaccin["iso_code"],vaccin["date"],vaccin["vaccines"])
            executeQuery(insert_stmt,data,cursor,conn)

    users = []
    for hospital in dico["hospitals"]:
        if not hospital["source_epidemiologiste"] in users:
            insert_stmt = (
                """INSERT INTO Utilisateurs (uuid, iso_code) 
                VALUES (%s,%s)"""
            )
            data = (hospital["source_epidemiologiste"], hospital["iso_code"])
            executeQuery(insert_stmt,data,cursor,conn)
            users.append(hospital["source_epidemiologiste"])

            insert_stmt = (
                """INSERT INTO Epidemiologistes (uuid_utilisateur)
                VALUES (%s)"""
            )
            data = [(hospital["source_epidemiologiste"])]
            executeQuery(insert_stmt, data, cursor, conn)
        
        insert_stmt = (
            """ INSERT INTO Hospitalisations (date, icu_patients, hosp_patients, iso_code, source_epidemiologiste)
            VALUES (%s,%s,%s,%s,%s)""" 
        )
        data = (datetime.strptime(hospital["date"], "%d/%m/%Y").isoformat(), hospital["icu_patients"], 
            hospital["hosp_patients"], hospital["iso_code"], hospital["source_epidemiologiste"])
        executeQuery(insert_stmt, data, cursor, conn)
print("script done.")
conn.commit()
conn.close()
