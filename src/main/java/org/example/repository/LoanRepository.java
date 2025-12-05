package org.example.repository;

import org.example.database.Database;
import org.example.models.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    // Kitap ödünç verme
    public void add(Loan loan) {
        String sql = "INSERT INTO loans(bookId, studentId, dateBorrowed, dateReturned) VALUES(?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, loan.getBookId());
            pstmt.setInt(2, loan.getStudentId());
            pstmt.setString(3, loan.getDateBorrowed());
            pstmt.setString(4, null); // İlk aldığında iade tarihi boştur

            pstmt.executeUpdate();
            System.out.println("Kitap ödünç verildi.");

        } catch (SQLException e) {
            System.out.println("Ödünç verme hatası: " + e.getMessage());
        }
    }

    // Bir kitabın şu an ödünçte olup olmadığını kontrol etme loans tablosunda bu kitap var mı ve iade tarihi null mı?
    public boolean isBookOnLoan(int bookId) {
        String sql = "SELECT count(*) FROM loans WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Eğer sayım 0'dan büyükse, kitap dışarıda demektir.
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Kontrol hatası: " + e.getMessage());
        }
        return false;
    }

    // Kitap iade alma (Update işlemi)
    public void returnBook(int bookId, String returnDate) {
        // İade tarihi boş olan (yani dışarıdaki) kaydı bul ve güncelle
        String sql = "UPDATE loans SET dateReturned = ? WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, returnDate);
            pstmt.setInt(2, bookId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Kitap başarıyla iade alındı.");
            } else {
                System.out.println("Bu kitap şu an ödünçte görünmüyor.");
            }

        } catch (SQLException e) {
            System.out.println("İade hatası: " + e.getMessage());
        }
    }

    // Tüm ödünç hareketlerini listeleme
    public List<Loan> getAll() {
        List<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}