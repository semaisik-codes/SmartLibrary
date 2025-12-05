package org.example.repository;

import org.example.database.Database;
import org.example.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    // 1. Kitap Ekleme
    public void add(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";

        // try bloğu parantezi içinde açılan bağlantılar otomatik kapanır.
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Soru işaretlerinin yerine değerleri koyuyoruz
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());

            pstmt.executeUpdate(); // Sorguyu çalıştır
            System.out.println("Kitap başarıyla eklendi: " + book.getTitle());

        } catch (SQLException e) {
            System.out.println("Kitap ekleme hatası: " + e.getMessage());
        }
    }

    // 2. Tüm Kitapları Listeleme
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Satır satır verileri oku
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                );
                bookList.add(book); // Listeye ekle
            }

        } catch (SQLException e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }
        return bookList;
    }

    // 3. ID ile tek kitap getirme. Ödünç verirken lazım olacak
    public Book getById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Book book = null;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                );
            }

        } catch (SQLException e) {
            System.out.println("Kitap bulma hatası: " + e.getMessage());
        }
        return book;
    }

    // 4. Kitap Silme
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Kitap silindi, ID: " + id);

        } catch (SQLException e) {
            System.out.println("Silme hatası: " + e.getMessage());
        }
    }

    // 5. Güncelleme
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());
            pstmt.setInt(4, book.getId());

            pstmt.executeUpdate();
            System.out.println("Kitap güncellendi.");

        } catch (SQLException e) {
            System.out.println("Güncelleme hatası: " + e.getMessage());
        }
    }
}