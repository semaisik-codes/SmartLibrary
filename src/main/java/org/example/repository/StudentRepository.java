package org.example.repository;

import org.example.database.Database;
import org.example.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public void add(Student student) {
        String sql = "INSERT INTO students(name, department) VALUES(?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.executeUpdate();
            System.out.println("Öğrenci eklendi: " + student.getName());

        } catch (SQLException e) {
            System.out.println("Öğrenci ekleme hatası: " + e.getMessage());
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }
        return list;
    }

    public Student getById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department")
                );
            }
        } catch (SQLException e) {
            System.out.println("Öğrenci bulma hatası: " + e.getMessage());
        }
        return student;
    }
}