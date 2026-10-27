import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        File file = new File("students.txt");

        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    double marks = Double.parseDouble(data[2]);

                    Student student = new Student(id, name, marks);
                    students.add(student);
                }

                reader.close();

            } catch (Exception e) {
                System.out.println("Error loading student data!");
            }
        }

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {

                System.out.print("Enter student ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                if (id <= 0) {
                    System.out.println("Invalid ID! ID must be greater than 0.");
                } else {

                    boolean exists = false;

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).id == id) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Student ID already exists!");
                    } else {

                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter student marks: ");
                        double marks = sc.nextDouble();

                        if (marks < 0 || marks > 100) {
                            System.out.println("Invalid marks! Enter marks between 0 and 100.");
                        } else {
                            Student student = new Student(id, name, marks);
                            students.add(student);

                            System.out.println("Student added successfully!");
                            saveStudents(students);
                        }
                    }
                }

            } else if (choice == 2) {



                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {

                    for (int i = 0; i < students.size(); i++) {
                        students.get(i).display();
                    }
                }

            } else if (choice == 3) {

                System.out.print("Enter student ID to search: ");
                int searchId = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < students.size(); i++) {

                    if (students.get(i).id == searchId) {
                        students.get(i).display();
                        found = true;
                        break;
                    }
                }

                if (found == false) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 4) {

                System.out.print("Enter student ID to delete: ");
                int deleteId = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < students.size(); i++) {

                    if (students.get(i).id == deleteId) {

                        students.remove(i);
                        found = true;

                        System.out.println("Student deleted successfully!");
                        saveStudents(students);
                        break;
                    }
                }

                if (found == false) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 5) {

                System.out.print("Enter student ID to update marks: ");
                int updateId = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < students.size(); i++) {

                    if (students.get(i).id == updateId) {


                        System.out.print("Enter new marks: ");
                        double newMarks = sc.nextDouble();

                        if (newMarks < 0 || newMarks > 100) {
                            System.out.println("Invalid marks! Enter marks between 0 and 100.");
                        } else {
                            students.get(i).marks = newMarks;

                            System.out.println("Student marks updated successfully!");
                            saveStudents(students);
                        }

                        found = true;
                        break;


                    }
                }

                if (found == false) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 6) {

                System.out.println("Thank you!");

            } else {

                System.out.println("Invalid choice!");
            }
        } while (choice != 6);
        sc.close();
    }
    public static void saveStudents(ArrayList<Student> students) {
        try {
            FileWriter writer = new FileWriter("students.txt");

            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);

                writer.write(s.id + "," + s.name + "," + s.marks + "\n");
            }
            writer.close();
            System.out.println("Student data saved successfully!");


        } catch (Exception e) {
            System.out.println("Error saving student data!");
        }
    }
}
