package com.example.kutuphaneotomasyonu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BorrowBook implements Initializable {
    @FXML
    private ListView<String> bookListview;

    @FXML
    private TextField bookSearch;

    @FXML
    private Button borrowButton;

    @FXML
    private ListView<String> userListview;

    @FXML
    private TextField userSearch;

    private List<String> bookList = new ArrayList<>();
    private List<String> userList = new ArrayList<>();

    Database database = new Database();
    Statement statement = database.getStatement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBookList();

        setUserList();

        userListview.getItems().addAll(userList);
        bookListview.getItems().addAll(bookList);

        userSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                userListview.getItems().clear();
                userListview.getItems().addAll(searchList(t1, userList));
            }
        });

        bookSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                bookListview.getItems().clear();
                bookListview.getItems().addAll(searchList(t1, bookList));
            }
        });

        borrowButton.setOnMouseClicked(mouseEvent -> {
            String username = userListview.getSelectionModel().getSelectedItem();
            String book = bookListview.getSelectionModel().getSelectedItem();

            Statement statement = database.getStatement();
            try {
                String sql = "INSERT INTO odunc_kitaplar (ad, kitap) VALUES ('" + username + "', '" + book + "')";
                statement.execute(sql);
                statement.close();

                openPage("main_page.fxml");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void setUserList() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT ad, soyad FROM uyeler");
            while (resultSet.next()){
                String name = resultSet.getString("ad");
                String surname = resultSet.getString("soyad");
                userList.add(name+" "+surname);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setBookList() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT ad FROM kitaplar");
            while (resultSet.next()){
                String name = resultSet.getString("ad");
                bookList.add(name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    private void openPage(String fxml){
        FXMLLoader FXML = new FXMLLoader(getClass().getResource(fxml));
        try {
            // aynı stage üzerine farklı bir fxml file actik
            Stage stage = (Stage) userSearch.getScene().getWindow();
            Scene scene = new Scene(FXML.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
























