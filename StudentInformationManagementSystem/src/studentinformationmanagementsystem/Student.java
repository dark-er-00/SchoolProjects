
package studentinformationmanagementsystem;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Student {
    private String name;
    private int age;
    private String course;
    private int studentId;
    
    public void addStudent(String name, int age, String course, int studentId){
        this.name = name;
        this.age = age;
        this.course = course;
        this.studentId = studentId;
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("StudentInfo.txt", true));
            writer.write(name + "," + age + "," + course + "," + studentId);
            writer.newLine();
            writer.flush();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }       
    }
    
    public void viewStudent(){
        ArrayList<String[]> studentInfo = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("StudentInfo.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String[] student = line.split(",");
                
                studentInfo.add(student);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        for(String[] student: studentInfo){
            System.out.println("Student Name: " + student[0] + "\nAge: " + student[1] + "\nCourse: " + student[2] + "\nStudent Id: " + student[3] + "\n");
        }
    }
    
    public void updateStudent(Scanner scanner){
        File oldFile = new File("StudentInfo.txt");
        File newFile = new File("Temporary.txt");
        boolean found = false;
        
        System.out.print("Enter the Student ID: ");
        String studentId = scanner.next();
        
        try(BufferedReader reader = new BufferedReader(new FileReader("StudentInfo.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("Temporary.txt", true))){
            String line;

            while((line = reader.readLine()) != null){
                String[] student = line.split(",");
                
                if(line.contains(studentId)){
                    System.out.println("Student Found: ");
                    System.out.println("Student Name: " + student[0]);
                    System.out.println("Age: " + student[1]);
                    System.out.println("Course: " + student[2]);
                    System.out.println("Student Id: " + student[3]);
                    
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("\t\tEnter New Information");
                    System.out.print("Student Name: ");
                    student[0] = scanner.next();
                    
                    System.out.print("Age: ");
                    student[1] = scanner.next();
                    
                    System.out.print("Course: ");
                    student[2] = scanner.next();
                    
                    writer.write(student[0] + "," + student[1] + "," + student[2] + "," + student[3]);
                    
                    found = true;
                } else {
                    writer.write(line);
                }   

                writer.newLine();
            }
            
            if(found){
                System.out.println("Information Successfully Changed");
            } else {
                    System.out.println("Student Not Found");
            }
            
            writer.close();
            reader.close();
            oldFile.delete();
            newFile.renameTo(oldFile);
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
    }

    public void deleteStudent(Scanner scanner){
        File oldFile = new File("StudentInfo.txt");
        File newFile = new File("Temporary.txt");
        boolean found = false;
        
        System.out.print("Enter the Student ID: ");
        String studentId = scanner.next();
        
        try(BufferedReader reader = new BufferedReader(new FileReader("StudentInfo.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("Temporary.txt", true))){
            String line;

            while((line = reader.readLine()) != null){
                if(!line.contains(studentId)){
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                } 
            }
            
            if(found){
                System.out.println("Student has been deleted.");
            }

            writer.close();
            reader.close();
            oldFile.delete();
            newFile.renameTo(oldFile);
            
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
    }

    public void searchStudent(Scanner scanner){
        System.out.print("Enter student id: ");
        String studentId = scanner.next();
        boolean notFound = false;

        try(BufferedReader reader = new BufferedReader(new FileReader("StudentInfo.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String[] student = line.split(",");

                if(student[3].equals(studentId)){
                    System.out.println("Student Found: ");
                    System.out.println("Student Name: " + student[0]);
                    System.out.println("Age: " + student[1]);
                    System.out.println("Course: " + student[2]);
                    System.out.println("Student Id: " + student[3]);
                    break;
                } else {
                    notFound = true;
                }
            }

            if(notFound){
                System.out.println("Student not found");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
