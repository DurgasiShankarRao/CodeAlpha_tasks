import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    static class Student {
        String name;
        int grade;

        Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Input names and grades
        for (int i = 0; i < numStudents; i++) {
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter grade for " + name + ": ");
            int grade = scanner.nextInt();
            students.add(new Student(name, grade));
        }

        int total = 0;
        int highest = students.get(0).grade;
        int lowest = students.get(0).grade;

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        double average = (double) total / students.size();

        System.out.println("\n--- Grade Report ---");
        for (Student s : students) {
            System.out.println(s.name + ": " + s.grade);
        }
        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        scanner.close();
    }
}
