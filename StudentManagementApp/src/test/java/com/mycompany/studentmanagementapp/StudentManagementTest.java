/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.studentmanagementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentManagementTest {

    @Test
    public void testAddStudentSuccessfully() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);

        boolean added = sm.addStudent(s1);
        assertTrue(added, "Student should be added successfully");
        assertEquals(1, sm.getStudentsSnapshot().length, "There should be 1 student stored");
    }

    @Test
    public void testAddDuplicateStudentId() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        Student s2 = new Student("Bob", 21, "S1", 90.0); // same ID

        sm.addStudent(s1);
        boolean addedDuplicate = sm.addStudent(s2);

        assertFalse(addedDuplicate, "Duplicate student ID should not be allowed");
        assertEquals(1, sm.getStudentsSnapshot().length, "Only one student should be stored");
    }

    @Test
    public void testSearchStudentFound() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        sm.addStudent(s1);

        Student result = sm.searchStudent("S1");
        assertNotNull(result, "Student should be found");
        assertEquals("Alice", result.getName(), "Name should match");
    }

    @Test
    public void testSearchStudentNotFound() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        sm.addStudent(s1);

        Student result = sm.searchStudent("S999");
        assertNull(result, "Student should not be found");
    }

    @Test
    public void testDeleteStudentSuccessfully() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        sm.addStudent(s1);

        boolean deleted = sm.deleteStudent("S1");
        assertTrue(deleted, "Student should be deleted");
        assertEquals(0, sm.getStudentsSnapshot().length, "No students should remain");
    }

    @Test
    public void testDeleteStudentNotFound() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        sm.addStudent(s1);

        boolean deleted = sm.deleteStudent("S999");
        assertFalse(deleted, "Delete should fail for non-existent student");
        assertEquals(1, sm.getStudentsSnapshot().length, "Original student should remain");
    }

    @Test
    public void testAverageMarks() {
        StudentManagement sm = new StudentManagement(3);
        sm.addStudent(new Student("Alice", 20, "S1", 80.0));
        sm.addStudent(new Student("Bob", 21, "S2", 90.0));

        double avg = sm.averageMarks();
        assertEquals(85.0, avg, 0.001, "Average should be correct");
    }

    @Test
    public void testAverageMarksEmptyList() {
        StudentManagement sm = new StudentManagement(3);
        
        double avg = sm.averageMarks();
        assertEquals(0.0, avg, 0.001, "Average should be 0 for empty list");
    }

    @Test
    public void testTopStudent() {
        StudentManagement sm = new StudentManagement(3);
        Student s1 = new Student("Alice", 20, "S1", 80.0);
        Student s2 = new Student("Bob", 21, "S2", 95.0);
        sm.addStudent(s1);
        sm.addStudent(s2);

        Student top = sm.topStudent();
        assertNotNull(top, "There should be a top student");
        assertEquals("Bob", top.getName(), "Top student should be Bob");
        assertEquals(95.0, top.getMarks(), 0.001, "Top student should have highest marks");
    }

    @Test
    public void testTopStudentEmptyList() {
        StudentManagement sm = new StudentManagement(3);
        
        Student top = sm.topStudent();
        assertNull(top, "Top student should be null for empty list");
    }

    @Test
    public void testAddStudentToFullCapacity() {
        StudentManagement sm = new StudentManagement(2); // Small capacity
        Student s1 = new Student("Alice", 20, "S1", 85.5);
        Student s2 = new Student("Bob", 21, "S2", 90.0);
        Student s3 = new Student("Charlie", 22, "S3", 78.0);

        assertTrue(sm.addStudent(s1), "First student should be added");
        assertTrue(sm.addStudent(s2), "Second student should be added");
        assertFalse(sm.addStudent(s3), "Third student should not be added (capacity full)");
        assertEquals(2, sm.getStudentsSnapshot().length, "Should have exactly 2 students");
    }
}