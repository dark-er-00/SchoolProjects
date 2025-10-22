package studentinformationmanagementsystem;

import java.util.Scanner;

public class StudentInformationManagementSystem {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        
        do{
        System.out.println("Student Information Management System");
        System.out.println("\tMAIN MENU");
        System.out.println("[1] - Add Student");
        System.out.println("[2] - View Students");
        System.out.println("[3] - Update Student");
        System.out.println("[4] - Delete Student");
        System.out.println("[5] - Search Student");
        System.out.println("[6] - Exit");
        System.out.print("Choice: ");
        choice = scanner.nextInt();
        System.out.println("----------------------------------------------------------------------");
        
        switch(choice){
            case 1:
                System.out.print("Student Name: ");
                String name = scanner.next();
                
                System.out.print("Age: ");
                int age = scanner.nextInt();
                
                System.out.print("Course: ");
                String course = scanner.next();
                
                System.out.print("Student Id: ");
                int studentId = scanner.nextInt();
                
                student.addStudent(name, age, course, studentId);
                
                System.out.println("Student Added!");
                System.out.println("----------------------------------------------------------------------");
                break;
                
            case 2:
                student.viewStudent();
                System.out.println("----------------------------------------------------------------------");
                break;
                
            case 3:
                student.updateStudent(scanner);
                System.out.println("----------------------------------------------------------------------");
                break;
                
            case 4:
            student.deleteStudent(scanner);
            System.out.println("----------------------------------------------------------------------");
                break;
                
            case 5:
            student.searchStudent(scanner);
            System.out.println("----------------------------------------------------------------------");
                break;
                
            case 6:
            System.out.println("Thank you for using the program.");
            System.exit(0);
                break;
                
            default:
                System.out.println("Invalid Input");
                break;
        }
        } while(choice != 6);
    }

}
