package com.example.kutuphaneotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    private Pane addBookPane;
    @FXML
    private Pane booksPane;
    @FXML
    private Pane borrowPane;
    @FXML
    private Pane infoPane;
    @FXML
    private Pane returnPane, addUserPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane logoutPane;


    private final AnchorPane infoPage = new FXMLLoader(getClass().getResource("library_info.fxml")).load();
    private final AnchorPane booksPage = new FXMLLoader(getClass().getResource("books.fxml")).load();
    private final AnchorPane addBookPage = new FXMLLoader(getClass().getResource("add_book.fxml")).load();
    private final AnchorPane borrowPage = new FXMLLoader(getClass().getResource("borrow_book.fxml")).load();
    private final AnchorPane returnPage = new FXMLLoader(getClass().getResource("return_book.fxml")).load();
    private final AnchorPane addUserPage = new FXMLLoader(getClass().getResource("add_user.fxml")).load();

    public MainPage() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(infoPage);

        infoPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(infoPage);
        });

        booksPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(booksPage);
        });

        addBookPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(addBookPage);
        });

        borrowPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(borrowPage);
        });

        returnPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(returnPage);
        });

        addUserPane.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(addUserPage);
        });

        logoutPane.setOnMouseClicked(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Başarıyla Çıkış Yapıldı!");
            alert.show();

            sayfayıAc("login_register_page.fxml");
        });
    }

    private void sayfayıAc(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // show register page
            Stage stage = (Stage) logoutPane.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

























