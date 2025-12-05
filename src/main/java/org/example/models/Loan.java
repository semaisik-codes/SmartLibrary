package org.example.models;

public class Loan {
    private int id;
    private int bookId;
    private int studentId;
    private String dateBorrowed; // Ödünç alma tarihi
    private String dateReturned; // İade tarihi (Henüz iade edilmediyse null olabilir)

    public Loan() {
    }

    // Tüm verilerle (Okuma işlemi için)
    public Loan(int id, int bookId, int studentId, String dateBorrowed, String dateReturned) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    // Yeni ödünç verirken (ID yok, İade tarihi yok çünkü daha yeni alıyor)
    public Loan(int bookId, int studentId, String dateBorrowed) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null; // Başlangıçta boş
    }

    // Getter ve Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getDateBorrowed() { return dateBorrowed; }
    public void setDateBorrowed(String dateBorrowed) { this.dateBorrowed = dateBorrowed; }

    public String getDateReturned() { return dateReturned; }
    public void setDateReturned(String dateReturned) { this.dateReturned = dateReturned; }
}