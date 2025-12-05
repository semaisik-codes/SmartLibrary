# SmartLibrary
Java, SQLite ve JDBC kullanılarak geliştirilmiş; OOP, Repository Pattern ve Maven yapılarına uygun Konsol Tabanlı Kütüphane Yönetim Sistemi.
Bu proje, bir üniversite kütüphanesinin kitap, öğrenci ve ödünç alma süreçlerini yönetmek amacıyla Java kullanılarak geliştirilmiş bir konsol uygulamasıdır. Veri kalıcılığı için SQLite veritabanı kullanılmıştır.

--Teknik Yapı ve Mimari--
Proje, Nesne Yönelimli Programlama (OOP) prensiplerine sadık kalınarak ve katmanlı mimari gözetilerek tasarlanmıştır.
Repository Design Pattern: Veritabanı İşlerimleri (CRUD) ile iş mantığ birbirinden ayrılmıştır. 
JDBC: SQL Injection güvenliği ve performans için PrepeardStatment yapısı kullanılmıştırç
Encapsulation: Tüm model sınıflarında (Book, Student, Loan) private değişkenler ve getter/setter metotları kullanılmıştır.
Koleksiyonlar: Veriler bellekte ArrayList ve List yapıları ile yönetilmiştir.
Kalıtım: Ortak özellikler BaseEntity sınıfında toplanarak kod tekrarı önlenmiştir.
Otomatik Veritabanı Kurulumu: Uygulama ilk çalıştığında library.db dosyasını ve gerekli tabloları otomatik oluşturur.

--Özellikler--
Uygulama üzerinden aşağıdaki işlemler gerçekleştirilebilir:
1- Kitap Yönetimi: Yeni kitap ekleme, kitapları listeleme.
2- Öğrenci Yönetimi: Yeni öğrenci kaydı, öğrencileri listeleme.
3- Ödünç İşlemleri: Öğrenciye ödünç kitap verme, bir kitap zaten başkasındayse sistem ödünç verilmesine izin vermez (kontrol mekanizması).
4- İade İşlemleri: Ödünçteki kitabı geri alma ve tarih güncelleme.
Raporlama: Mevcut ödünç hareketlerini ve durumlarını listeleme.

--Veritabanı Şeması (SQLite)--
books: id, title, author, year
students: id, name, department
loans: id, bookId, studentId, dateBorrowed, dateReturned

Proje dizininde Maven bağımlılıklarını yükleyin (pom.xml) ve Main.java sınıfını çalıştırın.

