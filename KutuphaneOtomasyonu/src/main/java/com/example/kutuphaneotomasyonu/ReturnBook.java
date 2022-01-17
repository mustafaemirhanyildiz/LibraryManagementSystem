package com.example.kutuphaneotomasyonu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ReturnBook implements Initializable {

    @FXML
    private TableColumn<ModelReturn, String> bookColumn;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<ModelReturn> tableView;
    @FXML
    private TableColumn<ModelReturn, String> userColumn;

    Database database = new Database();
    Statement statement = database.getStatement();

    ObservableList<ModelReturn> returnList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userColumn.setCellValueFactory(new PropertyValueFactory<ModelReturn, String>("username"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<ModelReturn, String>("book"));

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM odunc_kitaplar");
            while (resultSet.next()){
                String username = resultSet.getString("ad");
                String book = resultSet.getString("kitap");

                ModelReturn modelReturn = new ModelReturn(username, book);
                returnList.add(modelReturn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(returnList);

        returnButton.setOnMouseClicked(mouseEvent -> {
            Statement statement = database.getStatement();
            String book = tableView.getSelectionModel().getSelectedItem().getBook();

            try {
                statement.execute("DELETE FROM odunc_kitaplar WHERE kitap='" + book + "'");
                statement.close();

                openPage("main_page.fxml");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void openPage(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // aynı stage üzerine farklı bir fxml file actik
            Stage stage = (Stage) returnButton.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




















