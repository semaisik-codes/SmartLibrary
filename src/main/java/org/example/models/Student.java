package org.example.models;

public class Student {
    private int id;
    private String name;
    private String department;

    // Boş Constructor
    public Student() {
    }

    // Dolu Constructor (Veritabanından okurken)
    public Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // ID olmadan Constructor (Yeni kaydederken)
    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    // Getter ve Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}