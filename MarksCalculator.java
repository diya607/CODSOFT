import java.util.Scanner;

public class MarksCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numSubjects;
        System.out.print("Enter number of subjects: ");
        numSubjects = input.nextInt();

        int[] marks = new int[numSubjects];
        int total = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = input.nextInt();
            total += marks[i];
        }

        double average = (double) total / numSubjects;
        String grade;

        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("\n--- Result ---");
        System.out.println("Total Marks: " + total);
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("Grade: " + grade);
    }
}

