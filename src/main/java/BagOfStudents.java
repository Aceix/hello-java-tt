import java.util.ArrayList;

public class BagOfStudents {
    private final ArrayList<Student> students;

    BagOfStudents() {
        this.students = new ArrayList<>();
    }

    public BagOfStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student s) {
        this.students.add(s);
    }

    public void removeStudent(Student s) {
        this.students.remove(s);
    }

    public void clearStudents() {
        this.students.clear();
    }
}
