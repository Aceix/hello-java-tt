import java.util.*;
import java.util.stream.Collectors;

public class Register {

    private List<? extends Student> students;

    public Register() {}

    public Register(List<? extends Student> students) {
        this.students = students;
    }

    public List<String> getRegister() {
        return this.students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public Map<Level, List<Student>> getRegisterByLevel(Level level) {

        List<Student> names = this.students.stream()
            .filter(s -> s.getLevel().equals(level))
            .collect(Collectors.toList());

        return new HashMap<Level, List<Student>>() {{
            put(level, names);
        }};
    }

    public String printReport() {
        var wrapper = new Object() { String out = ""; };
        var studentsByLevel = new EnumMap<Level, HashSet<Student>>(Level.class);

//        re-arrange students
        this.students.stream()
            .forEach(student -> {
                var l = ((Student) student).getLevel();
                if (!studentsByLevel.containsKey(l)) {
                    studentsByLevel.put(l, new HashSet<>());
                }
                studentsByLevel.get(l).add((Student) student);
            });

//        print students
        studentsByLevel.forEach((level, students) -> {
            wrapper.out += "\n";
            wrapper.out += "===========================\n";
            wrapper.out += "Level " + level + "\n";
            wrapper.out += "\n";

            for(var s : students) {
                wrapper.out += s.getName() + "\n";
            }
        });

        System.out.println(wrapper.out);

        return wrapper.out;
    }

    List<? extends Student> sort(Comparator<Student> c) {
        var x = new ArrayList<>(this.students);

        x.sort(c);

        return x;
    }

    Student getStudentByName(String name) throws StudentNotFoundException {
        return this.students.stream()
                .filter(s -> s.getName().equals(name))
                .findAny()
                .orElseThrow(
                    StudentNotFoundException::new
                );
    }

    public double getHighestGrade() throws NoSuchElementException {
        return this.students.stream()
                .flatMapToDouble(Student::getGradesAsDoubleStream)
                .max()
                .getAsDouble();
    }

    public double getAverageGrade() throws NoSuchElementException {
        return this.students.stream()
                .flatMapToDouble(Student::getGradesAsDoubleStream)
                .average()
                .getAsDouble();
    }

    public List<Double> getGradesAbove60() {
        return this.students.stream()
                .flatMapToDouble(Student::getGradesAsDoubleStream)
                .filter(grade -> grade > 60)
                .boxed()
                .collect(Collectors.toList());
    }

    Optional<Student> getOptionalStudentByName(String name) {
        return (Optional<Student>) this.students.stream()
                .filter(s -> s.getName().equals(name))
                .findAny();
    }

    public List<Student> getStudentsByName(List<String> names) {
        return this.students.stream()
                .filter(student -> names.contains(student.getName()))
                .collect(Collectors.toList());
    }
}
