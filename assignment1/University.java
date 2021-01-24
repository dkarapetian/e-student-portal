import java.util.*;
public class University {
    public static void main(String[] args) {
        new University().use();
    }
    private LinkedList<Subject> subjects = new LinkedList<Subject>();
    private LinkedList<Student> students = new LinkedList<Student>();

    public University() {
        subjects.add(new Subject(48024, "Applications Programming"));
        subjects.add(new Subject(31284, "Web Services Development"));
        subjects.get(0).addActivity("Lec1", 1, "Wed", 18, 1, "CB11.00.405", 200);
        subjects.get(0).addActivity("Cmp1", 1, "Wed", 19, 2, "CB11.B1.403", 2);
        subjects.get(0).addActivity("Cmp1", 2, "Wed", 19, 2, "CB11.B1.401", 2);
        subjects.get(0).addActivity("Cmp1", 3, "Wed", 19, 2, "CB11.B1.402", 2);
        subjects.get(1).addActivity("Lec1", 1, "Tue", 16, 1, "CB02.03.002", 160);
        subjects.get(1).addActivity("Cmp1", 1, "Tue", 9, 2, "CB11.B1.102", 30);
        subjects.get(1).addActivity("Cmp1", 2, "Tue", 9, 2, "CB11.B1.103", 30);
        subjects.get(1).addActivity("Cmp1", 3, "Tue", 14, 2, "CB11.B1.102", 30);
        subjects.get(1).addActivity("Cmp1", 4, "Tue", 14, 2, "CB11.B1.103", 30);
    }

    public void use(){
        char choice;
        while ((choice = readChoice()) != 'x') {
            switch (choice) {
                case 'a': addStudent(); break;
                case 'r': removeStudent(); break;
                case 'v': viewStudents(); break;
                case 'l': login(); break;
                default : help(); break;
            }
        }
    }

    private char readChoice() {
        System.out.print("Choice (a/r/v/l/x): ");
        return In.nextChar();
    }

    public void addStudent() {
        System.out.print("Number: ");
        String number = In.nextLine();

        boolean exists = false;

        for (Student student : students) {
            if (student.getNumber().equals(number)) {
                exists = true; 
                System.out.println("Student number already exists");
            }
        }
        if (exists == false) {
            System.out.print("Name: ");
            String name = In.nextLine();
            students.add(new Student(number, name)); 
        }
    }

    public void removeStudent() {
        System.out.print("Number: ");
        String number = In.nextLine();
        boolean exists = false;

        for (Iterator<Student> it = students.iterator(); it.hasNext();){
            Student s = it.next();
            if (s.getNumber().equals(number)) {
                s.withdrawAll();
                it.remove(); 
                exists = true;
            }
        }
        if (exists == false) {
            System.out.println("No such student");
        }
    }

    public void viewStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void login() {
        boolean exists = false;
        System.out.print("Number: ");
        String number = In.nextLine();
        for (Student student : students) {
            if (student.getNumber().equals(number)) {
                student.use(subjects);
                exists = true;
            }
        }
        if (exists == false) {
                System.out.println("No such student");
            }
    }

    private void help() {
        System.out.println("University menu options");
        System.out.println("a = add a student");
        System.out.println("r = remove a student");
        System.out.println("v = view all students");
        System.out.println("l = login");
        System.out.println("x = exit");
    }
}