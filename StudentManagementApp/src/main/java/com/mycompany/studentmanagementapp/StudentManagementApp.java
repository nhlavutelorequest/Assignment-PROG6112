/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmanagementapp;

import java.util.Arrays;
import java.util.Scanner;

/* 
   Domain classes
 */

class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Encapsulation
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

class Student extends Person {
    private String studentId;
    private double marks;

    public Student(String name, int age, String studentId, double marks) {
        super(name, age);
        this.studentId = studentId;
        this.marks = marks;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String toReportLine() {
        return String.format("ID: %-6s | Name: %-15s | Age: %2d | Marks: %6.2f",
                studentId, getName(), getAge(), marks);
    }
}

/*
   Array-based manager
*/

class StudentManagement {
    private final Student[] students;
    private int count;

    public StudentManagement(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size must be > 0");
        this.students = new Student[size];
        this.count = 0;
    }

    /** Add a student if capacity allows. Returns true if added, false if full or duplicate ID. */
    public boolean addStudent(Student s) {
        if (s == null) return false;

        // Prevent duplicate IDs
        if (searchStudent(s.getStudentId()) != null) {
            System.out.println("️ Duplicate ID. Not added.");
            return false;
        }

        if (count < students.length) {
            students[count++] = s;
            System.out.println(" Student added.");
            return true;
        } else {
            System.out.println(" No space left in the system.");
            return false;
        }
    }

    /** Linear search by ID (array + loop). */
    public Student searchStudent(String studentId) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(studentId)) {
                return students[i];
            }
        }
        return null;
    }

    /** Delete by ID, shifting array left to keep it compact. */
    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(studentId)) {
                // shift left
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--count] = null;
                return true;
            }
        }
        return false;
    }

    /** Snapshot of active students . */
    public Student[] getStudentsSnapshot() {
        return Arrays.copyOf(students, count);
    }

    /** Console output. */
    public void displayReport() {
        System.out.println("\n========== STUDENT REPORT ==========");
        if (count == 0) {
            System.out.println("No students captured yet.");
        } else {
            System.out.println("Total students: " + count);
            for (int i = 0; i < count; i++) {
                System.out.println(students[i].toReportLine());
            }
            System.out.printf("Average marks: %.2f%n", averageMarks());
            Student top = topStudent();
            if (top != null) {
                System.out.println("Top student : " + top.toReportLine());
            }
        }
        System.out.println("====================================\n");
    }

    /** Helper methods to show looping over arrays. */
    public double averageMarks() {
        if (count == 0) return 0;
        double sum = 0;
        for (int i = 0; i < count; i++) sum += students[i].getMarks();
        return sum / count;
    }

    public Student topStudent() {
        if (count == 0) return null;
        Student best = students[0];
        for (int i = 1; i < count; i++) {
            if (students[i].getMarks() > best.getMarks()) best = students[i];
        }
        return best;
    }
}

/* 
   Console application
*/

public class StudentManagementApp {

    private static void menu() {
        System.out.println("\n--- Student Management Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. Search Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Display Report");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement(50); // capacity

        while (true) {
            try {
                menu();
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1: {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter age: ");
                        int age = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter student ID: ");
                        String id = sc.nextLine().trim();
                        System.out.print("Enter marks: ");
                        double marks = Double.parseDouble(sc.nextLine().trim());

                        sm.addStudent(new Student(name, age, id, marks));
                        break;
                    }
                    case 2: {
                        System.out.print("Enter student ID to search: ");
                        String searchId = sc.nextLine().trim();
                        Student found = sm.searchStudent(searchId);
                        if (found != null) {
                            System.out.println("Found: " + found.toReportLine());
                        } else {
                            System.out.println(" Student not found.");
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Enter student ID to delete: ");
                        String delId = sc.nextLine().trim();
                        boolean ok = sm.deleteStudent(delId);
                        System.out.println(ok ? "️ Deleted." : " Not found.");
                        break;
                    }
                    case 4:
                        sm.displayReport();
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try 1–5.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("️ Please enter valid numeric values where required.");
            }
        }
    }
}
