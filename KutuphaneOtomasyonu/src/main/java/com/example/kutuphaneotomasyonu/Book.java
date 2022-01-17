package com.example.kutuphaneotomasyonu;

public class Book {

    private String name, writer, type;
    private int page;

    public Book(String name, String writer, String type, int page) {
        this.name = name;
        this.writer = writer;
        this.type = type;
        this.page = page;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
