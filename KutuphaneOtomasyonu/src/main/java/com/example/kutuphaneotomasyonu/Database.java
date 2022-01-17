package com.example.kutuphaneotomasyonu;

import java.sql.*;

public class Database {

    private Statement statement;

    public Statement getStatement(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane_otomasyonu", "root", "159753123Aa");
            statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }
}