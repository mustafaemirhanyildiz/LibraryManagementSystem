package com.example.kutuphaneotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddBook implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField pageTextfield;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField writerTextfield;

    Database database = new Database();
    Statement statement = database.getStatement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTypeBoxItems();

        addButton.setOnMouseClicked(mouseEvent -> {
            String name = nameTextfield.getText();
            String writer = writerTextfield.getText();
            int page = 0;
            if(!pageTextfield.getText().isEmpty())
                page = Integer.parseInt(pageTextfield.getText());

            String type = typeBox.getValue();

            if(name.isEmpty() || writer.isEmpty() || type.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Tüm Alanları Doldurunuz!");
                alert.show();
            }
            else{
                // verileri veritabanına kaydet
                saveData(name, writer, type, page);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Kitap Başarıyla Eklendi!");
                alert.show();

                openPage("main_page.fxml");
            }

        });
    }

    private void saveData(String name, String writer, String type, int page) {
        try {

            statement.execute("INSERT INTO kitaplar (ad, yazar, sayfa, tür) VALUES ('" + name + "', '" + writer + "', " + page + ", '" + type + "')");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTypeBoxItems() {
        String[] typeArray = {"Roman", "Hikaye", "Bilim", "Anı", "Biyografi"};
        typeBox.getItems().addAll(typeArray);
    }

    private void openPage(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // aynı stage üzerine farklı bir fxml file actik
            Stage stage = (Stage) pageTextfield.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

































