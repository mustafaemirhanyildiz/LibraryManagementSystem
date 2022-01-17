module com.example.kutuphaneotomasyonu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kutuphaneotomasyonu to javafx.fxml;
    exports com.example.kutuphaneotomasyonu;
}