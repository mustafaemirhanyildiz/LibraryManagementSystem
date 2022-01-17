package com.example.kutuphaneotomasyonu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Books implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, Integer> pageColumn;
    @FXML
    private TableColumn<Book, String> typeColumn;
    @FXML
    private TableColumn<Book, String> writerColumn;
    @FXML
    private TextField searchBar;

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    private final Database database = new Database();
    private final Statement statement = database.getStatement();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        writerColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("writer"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("type"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("page"));

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM kitaplar");
            while (resultSet.next()){
                String name = resultSet.getString("ad");
                String writer = resultSet.getString("yazar");
                String type = resultSet.getString("tür");
                int page = resultSet.getInt("sayfa");

                Book book = new Book(name, writer, type, page);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                bookTable.getItems().clear();
                bookTable.setItems(searchList(t1, bookList));
            }
        });*/

        bookTable.setItems(bookList);
    }

    /*private List<String> searchList(String searchWords, ObservableList<Book> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }*/
}
























