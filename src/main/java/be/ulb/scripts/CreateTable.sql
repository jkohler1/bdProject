SET @@time_zone = '+01:00';
CREATE table IF NOT EXISTS Climats
(
id integer primary key auto_increment,
description varchar(100) NOT NULL
);
CREATE table IF NOT EXISTS Continents
(
id integer primary key auto_increment,
nom varchar(50) NOT NULL
);
CREATE table IF NOT EXISTS Regions
(
id integer primary key auto_increment,
nom varchar(50) NOT NULL,
continent_id integer NOT NULL,
foreign key (continent_id) references Continents(id)
);
CREATE table IF NOT EXISTS Pays
(
iso_code char(3) primary key,
nom varchar(50) NOT NULL,
hdi decimal(4,3) NOT NULL,
population integer NOT NULL,
superficie decimal(54,2) NOT NULL,
region_id integer NOT NULL,
climat_id integer NULL,
foreign key (region_id) references Regions(id),
foreign key (climat_id) references Climats(id)
);
CREATE table IF NOT EXISTS Tests
(
id integer primary key auto_increment,
date DATE NOT NULL,
tests integer NOT NULL,
vaccinations integer NOT NULL,
iso_code char(3) NOT NULL,
foreign key (iso_code) references Pays(iso_code)
);
CREATE table IF NOT EXISTS Utilisateurs
(
uuid char(36) primary key,
nom varchar(50) NULL,
prenom varchar(50) NULL,
nom_utilisateur varchar(50) NULL,
ad_rue varchar(50) NULL,
ad_numero varchar(50) NULL,
ad_code_postal varchar(50) NULL,
ad_ville varchar(50) NULL,
password varchar(50) NULL,
iso_code char(3) NOT NULL,
foreign key (iso_code) references Pays(iso_code)
);
CREATE table IF NOT EXISTS Epidemiologistes
(
uuid_utilisateur char(36) primary key,
centre varchar(50) NULL,
tel varchar (50) NULL,
foreign key (uuid_utilisateur) references Utilisateurs(uuid)
);
CREATE table IF NOT EXISTS Hospitalisations
(
id integer primary key auto_increment,
date DATE NOT NULL,
icu_patients integer NOT NULL,
hosp_patients integer NOT NULL,
iso_code char(3) NOT NULL,
source_epidemiologiste char(36) NOT NULL,
foreign key (iso_code) references Pays(iso_code),
foreign key (source_epidemiologiste) references Epidemiologistes(uuid_utilisateur)
);
CREATE table IF NOT EXISTS Vaccins
(
    id_vaccin integer primary key auto_increment,
    vaccins varchar(55) NOT NULL
);
CREATE table IF NOT EXISTS Vaccins_Pays
(
    id integer primary key auto_increment,
    iso_code char(3) NOT NULL,
    id_vaccin integer NOT NULL,
    date DATE NOT NULL,
    foreign key (id_vaccin) references Vaccins(id_vaccin),
    foreign key (iso_code) references Pays(iso_code)
);