package org.example.models;

public class Book {
    // 1. Değişkenler (Private yaparak dışarıdan doğrudan erişimi kapattım)
    private int id;
    private String title;
    private String author;
    private int year;

    // 2. Boş Constructor (Veritabanından veri çekerken lazım olabilir)
    public Book() {
    }

    // 3. Dolu Constructor (Yeni bir kitap oluştururken kolayca değer atamak için)
    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // ID olmadan Constructor (Veritabanına yeni eklerken ID otomatik oluşacağı için ID verilmedi)
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // 4. Getter ve Setter Metotları (Değişkenlere güvenli erişim için)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}