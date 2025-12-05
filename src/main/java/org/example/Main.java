package org.example;

import org.example.database.Database;
import org.example.models.Book;
import org.example.models.Loan;
import org.example.models.Student;
import org.example.repository.BookRepository;
import org.example.repository.LoanRepository;
import org.example.repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Her yerden erişebilmek için Repository nesnelerini oluştur
    static BookRepository bookRepo = new BookRepository();
    static StudentRepository studentRepo = new StudentRepository();
    static LoanRepository loanRepo = new LoanRepository();

    // Kullanıcıdan veri almak için Scanner
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 1. Program başlarken veri tabanını ve tabloları hazırla
        System.out.println("Sistem başlatılıyor...");
        Database.createNewDatabase();

        // 2. Sonsuz döngü ile menüyü sürekli göster
        while (true) {
            System.out.println("\n=== SMART LIBRARY KÜTÜPHANE SİSTEMİ ===");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitapları Listele");
            System.out.println("3. Öğrenci Ekle");
            System.out.println("4. Öğrencileri Listele");
            System.out.println("5. Kitap Ödünç Ver");
            System.out.println("6. Ödünç Listesini Görüntüle");
            System.out.println("7. Kitap İade Al");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = -1;
            try {
                secim = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz!");
                continue;
            }

            switch (secim) {
                case 1:
                    kitapEkle();
                    break;
                case 2:
                    kitaplariListele();
                    break;
                case 3:
                    ogrenciEkle();
                    break;
                case 4:
                    ogrencileriListele();
                    break;
                case 5:
                    oduncVer();
                    break;
                case 6:
                    oduncListele();
                    break;
                case 7:
                    iadeAl();
                    break;
                case 0:
                    System.out.println("Programdan çıkılıyor. İyi günler!");
                    return; // main metodunu sonlandırır
                default:
                    System.out.println("Hatalı seçim, tekrar deneyin.");
            }
        }
    }

    // MENÜ İŞLEVLERİ (METOTLAR)

    private static void kitapEkle() {
        System.out.print("Kitap Adı: ");
        String title = scanner.nextLine();

        System.out.print("Yazar: ");
        String author = scanner.nextLine();

        System.out.print("Yıl: ");
        int year = Integer.parseInt(scanner.nextLine()); // nextInt yerine bunu kullanmak daha güvenli (satır atlama sorunu olmaz)

        Book newBook = new Book(title, author, year);
        bookRepo.add(newBook);
    }

    private static void kitaplariListele() {
        System.out.println("\n--- KİTAP LİSTESİ ---");
        List<Book> books = bookRepo.getAll();

        if (books.isEmpty()) {
            System.out.println("Kayıtlı kitap yok.");
        } else {
            // foreach döngüsü ile listeyi gez
            for (Book b : books) {
                System.out.println("ID: " + b.getId() + " | " + b.getTitle() + " - " + b.getAuthor() + " (" + b.getYear() + ")");
            }
        }
    }

    private static void ogrenciEkle() {
        System.out.print("Öğrenci Adı: ");
        String name = scanner.nextLine();

        System.out.print("Bölüm: ");
        String dept = scanner.nextLine();

        Student student = new Student(name, dept);
        studentRepo.add(student);
    }

    private static void ogrencileriListele() {
        System.out.println("\n--- ÖĞRENCİ LİSTESİ ---");
        List<Student> students = studentRepo.getAll();

        if (students.isEmpty()) {
            System.out.println("Kayıtlı öğrenci yok.");
        } else {
            for (Student s : students) {
                System.out.println("ID: " + s.getId() + " | " + s.getName() + " (" + s.getDepartment() + ")");
            }
        }
    }

    private static void oduncVer() {
        System.out.println("\n--- ÖDÜNÇ VERME ---");

        // Önce kitapları listeledim ki kullanıcı ID seçebilsin
        kitaplariListele();
        System.out.print("Ödünç verilecek Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        // kitap kullanımda mı kontrolü
        if (loanRepo.isBookOnLoan(bookId)) {
            System.out.println("HATA: Bu kitap şu an zaten başkasında!");
            return; // İşlemi iptal et
        }

        ogrencileriListele();
        System.out.print("Öğrenci ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Tarih (Örn: 2025-12-04): ");
        String date = scanner.nextLine();

        Loan loan = new Loan(bookId, studentId, date);
        loanRepo.add(loan);
    }

    private static void oduncListele() {
        System.out.println("\n--- ÖDÜNÇ HAREKETLERİ ---");
        List<Loan> loans = loanRepo.getAll();

        for (Loan l : loans) {
            String durum = (l.getDateReturned() == null) ? "ÖDÜNÇTE" : "İADE EDİLDİ (" + l.getDateReturned() + ")";
            System.out.println("Kitap ID: " + l.getBookId() + " -> Öğrenci ID: " + l.getStudentId() + " | Durum: " + durum);
        }
    }

    private static void iadeAl() {
        System.out.println("\n--- KİTAP İADE AL ---");
        System.out.print("İade edilen Kitap ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("İade Tarihi (Örn: 2025-12-10): ");
        String date = scanner.nextLine();

        loanRepo.returnBook(bookId, date);
    }
}