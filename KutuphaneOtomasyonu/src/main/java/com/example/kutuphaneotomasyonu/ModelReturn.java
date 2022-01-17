package com.example.kutuphaneotomasyonu;

public class ModelReturn {

    private String username, book;

    public ModelReturn(String username, String book) {
        this.username = username;
        this.book = book;
    }

    public ModelReturn() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
