package com.example.kutuphaneotomasyonu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRegisterPage {

    @FXML
    private AnchorPane MainPanel;

    @FXML
    private TextField TextLoginUserName;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private AnchorPane registerPane;

    @FXML
    private PasswordField textLoginPassword;

    @FXML
    private PasswordField textRegisterPassword;

    @FXML
    private PasswordField textRegisterPasswordAgain;

    @FXML
    private TextField textRegisterUsername;

    private Database database = new Database();

    @FXML
    void goLoginPageClick(ActionEvent event) {
        registerPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    void goRegisterPageClick(ActionEvent event) {
        registerPane.setVisible(true);
        loginPane.setVisible(false);
    }

    @FXML
    void loginClick(ActionEvent event) {
        Statement statement = database.getStatement();
        Statement statement1 = database.getStatement();
        Statement statement2 = database.getStatement();

        String ad = TextLoginUserName.getText();
        String sifre = textLoginPassword.getText();
        boolean control = true;

        if(ad.isEmpty() || sifre.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Giriş Başarısız!");
            alert.setContentText("Lütfen boş alan bırakmayınız!");
            alert.show();
        }
        else{
            try {
                ResultSet resultSet = statement.executeQuery("SELECT ad, sifre FROM kullanicilar");
                while(resultSet.next()){
                    if(ad.equals(resultSet.getString("ad")) && sifre.equals(resultSet.getString("sifre"))){
                        // giris yap
                        statement2.execute("UPDATE kullanicilar SET durum='offline'");
                        statement1.execute("UPDATE kullanicilar SET durum='online' WHERE sifre='" + sifre + "'");

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Giriş Başarılı!");
                        alert.show();

                        control = false;
                        sayfayıAc("main_page.fxml");
                    }
                }
                if(control){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Giriş Başarısız!");
                    alert.setContentText("Lütfen bilgilerinizi kontrol ediniz!");
                    alert.show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void registerClick(ActionEvent event) {
        String ad = textRegisterUsername.getText();
        String sifre = textRegisterPassword.getText();
        String sifreTekrar = textRegisterPasswordAgain.getText();

        Statement statement = database.getStatement();
        Statement statement1 = database.getStatement();

        try {
            // diger kullanicilar offline oldu
            statement1.execute("UPDATE kullanicilar SET durum='offline'");
            statement1.close();


            statement.execute("INSERT INTO kullanicilar (ad, sifre, durum) VALUES ('" + ad + "', '" + sifre + "', 'online')");
            statement.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Kayıt Başarılı!");
            alert.show();

            sayfayıAc("main_page.fxml");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sayfayıAc(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // show register page
            Stage stage = (Stage) textLoginPassword.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




















