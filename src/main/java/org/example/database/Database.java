package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // Veri tabanı dosyasının adı. Proje klasöründe otomatik oluşacak.
    private static final String DB_URL = "jdbc:sqlite:library.db";

    // 1. Veri tabanına bağlanma metodu
    public static Connection connect() {
        Connection conn = null;
        try {
            // Bağlantıyı oluşturuyor
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
        return conn;
    }

    // 2. Tabloları oluşturma metodu
    public static void createNewDatabase() {

        // Kitaplar tablosu SQL komutu
        String sqlBooks = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " title TEXT NOT NULL,\n"
                + " author TEXT NOT NULL,\n"
                + " year INTEGER\n"
                + ");";

        // Öğrenciler tablosu SQL komutu
        String sqlStudents = "CREATE TABLE IF NOT EXISTS students (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " department TEXT\n"
                + ");";

        // Ödünç alma işlemleri tablosu SQL komutu
        String sqlLoans = "CREATE TABLE IF NOT EXISTS loans (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " bookId INTEGER,\n"
                + " studentId INTEGER,\n"
                + " dateBorrowed TEXT,\n"
                + " dateReturned TEXT\n"
                + ");";

        // Bağlantıyı açıp komutları çalıştır
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            // Komutları sırayla çalıştır
            stmt.execute(sqlBooks);
            stmt.execute(sqlStudents);
            stmt.execute(sqlLoans);

            System.out.println("Tablolar başarıyla oluşturuldu.");

        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}
