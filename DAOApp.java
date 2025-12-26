package daoapp;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import za.ac.tut.bl.StudentDB;
import za.ac.tut.entity.Student;

/**
 * Entry point of the application.
 * Demonstrates how the DAO layer is used
 * to persist a Student object.
 */

public class DAOApp {

    public static void main(String[] args) throws SQLException {
        //Connect to the Database.
        String dbURL = "jdbc:derby://localhost:1527/StudentsDB";
        String username = "app";
        String password = "123";

        StudentDB studentdb = new StudentDB(dbURL, username, password);

        
        String name = JOptionPane.showInputDialog(null, "Enter student name");
        String surname = JOptionPane.showInputDialog(null, "Enter student surname");
        Integer studentNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter student number"));

        
        Student student = new Student(studentNumber, surname, name);
        studentdb.add(student);

        
        JOptionPane.showMessageDialog(null, "Student created succesfully");
    }

}
