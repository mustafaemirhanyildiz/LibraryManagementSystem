package com.example.kutuphaneotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LibraryInfo implements Initializable {

    @FXML
    private Label kitapSayiText;

    @FXML
    private Label kullaniciSayiText;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement statement = database.getStatement();
        int bookValue=0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM kitaplar");
            while (resultSet.next()){
                bookValue++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        kitapSayiText.setText(bookValue + " Kitap");

        Statement statement1 = database.getStatement();
        int userValue=0;

        try {
            ResultSet resultSet = statement1.executeQuery("SELECT * FROM uyeler");
            while (resultSet.next()){
                userValue++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        kullaniciSayiText.setText(userValue + " Kullanıcı");
    }
}




























