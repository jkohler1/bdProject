module bd.projet {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires spring.security.crypto;

    opens be.ulb;
    opens be.ulb.controllers;
    opens be.ulb.controllers.views;
    opens be.ulb.models;
}