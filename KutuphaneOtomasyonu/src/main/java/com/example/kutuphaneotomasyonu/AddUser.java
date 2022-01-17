package com.example.kutuphaneotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddUser implements Initializable {

    @FXML
    private Button addUserButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addUserButton.setOnMouseClicked(mouseEvent -> {
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            String date = datePicker.getValue().toString();

            if(name.isEmpty() || surname.isEmpty() || date.isEmpty()){
                // üye ekleme
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Üye Eklenemedi!");
                alert.setContentText("Lütfen tüm alanları doldurduğunuzdan emin olunuz...");
                alert.show();
            }
            else{
                // üye ekle
                Statement statement = database.getStatement();
                try {
                    statement.execute("INSERT INTO uyeler (ad, soyad, dogum_tarihi) VALUES ('" + name + "', '" + surname + "', '" + date + "')");
                    statement.close();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Üye Başarıyla Eklendi!");
                    alert.show();

                    openPage("main_page.fxml");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openPage(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // aynı stage üzerine farklı fxml actik
            Stage stage = (Stage) addUserButton.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






















