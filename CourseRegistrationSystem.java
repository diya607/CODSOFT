import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean register(String studentId) {
        if (registeredStudents.size() < capacity && !registeredStudents.contains(studentId)) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean remove(String studentId) {
        return registeredStudents.remove(studentId);
    }

    public int availableSlots() {
        return capacity - registeredStudents.size();
    }

    public void displayInfo() {
        System.out.println(code + " - " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + availableSlots());
        System.out.println();
    }
}

class Student {
    String studentId, name;
    List<String> registeredCourseCodes;

    public Student(String id, String name) {
        this.studentId = id;
        this.name = name;
        this.registeredCourseCodes = new ArrayList<>();
    }

    public void registerCourse(String code) {
        registeredCourseCodes.add(code);
    }

    public void dropCourse(String code) {
        registeredCourseCodes.remove(code);
    }

    public boolean isRegistered(String code) {
        return registeredCourseCodes.contains(code);
    }

    public void displayCourses() {
        if (registeredCourseCodes.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses: " + registeredCourseCodes);
        }
    }
}

public class CourseRegistrationSystem {
    static Map<String, Course> courseDB = new HashMap<>();
    static Map<String, Student> studentDB = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadCourses();
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        Student student = new Student(id, name);
        studentDB.put(id, student);

        int option;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1:
                    showCourses();
                    break;
                case 2:
                    registerForCourse(student);
                    break;
                case 3:
                    dropCourse(student);
                    break;
                case 4:
                    student.displayCourses();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 5);
    }

    public static void loadCourses() {
        courseDB.put("CS101", new Course("CS101", "Intro to CS", "Basics of computer science", 3, "Mon 10AM"));
        courseDB.put("MA102", new Course("MA102", "Calculus I", "Introduction to calculus", 2, "Tue 11AM"));
        courseDB.put("PH103", new Course("PH103", "Physics I", "Mechanics and motion", 2, "Wed 9AM"));
    }

    public static void showCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courseDB.values()) {
            c.displayInfo();
        }
    }

    public static void registerForCourse(Student student) {
        System.out.print("Enter course code to register: ");
        String code = sc.nextLine();
        Course c = courseDB.get(code);

        if (c == null) {
            System.out.println("Course not found.");
        } else if (student.isRegistered(code)) {
            System.out.println("Already registered for this course.");
        } else if (c.register(student.studentId)) {
            student.registerCourse(code);
            System.out.println("Successfully registered for " + code);
        } else {
            System.out.println("Registration failed. Course might be full.");
        }
    }

    public static void dropCourse(Student student) {
        System.out.print("Enter course code to drop: ");
        String code = sc.nextLine();
        Course c = courseDB.get(code);

        if (c == null) {
            System.out.println("Course not found.");
        } else if (!student.isRegistered(code)) {
            System.out.println("You're not registered in this course.");
        } else {
            c.remove(student.studentId);
            student.dropCourse(code);
            System.out.println("Dropped course: " + code);
        }
    }
}
