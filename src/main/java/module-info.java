module bd.projet {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens be.ulb;
    opens be.ulb.controllers;
    opens be.ulb.controllers.views;
    opens be.ulb.models;
}