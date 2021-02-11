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
        return new HashMap<>() {{
            put(
                    level,
                    students.stream()
                            .filter(s -> s.getLevel().equals(level))
                            .collect(Collectors.toList())
            );
        }};
    }

    public String printReport() {
        var sb = new StringBuilder();
        var studentsByLevel = new EnumMap<Level, HashSet<Student>>(Level.class);

//        re-arrange students
        this.students.stream()
                .forEach(student -> {
                    var l = (student).getLevel();
                    if (!studentsByLevel.containsKey(l)) {
                        studentsByLevel.put(l, new HashSet<>());
                    }
                    studentsByLevel.get(l).add(student);
                });

//        print students
        studentsByLevel.forEach((level, students) -> {
            sb.append("\n")
                    .append("===========================\n")
                    .append("Level ").append(level).append("\n")
                    .append("\n");

            for(var s : students) {
                sb.append(s.getName()).append("\n");
            }
        });

        System.out.println(sb.toString());

        return sb.toString();
    }

    List<? extends Student> sort(Comparator<Student> c) {
        return this.students.stream()
                .sorted(c)
                .collect(Collectors.toList());
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
                .orElse(0.0);
    }

    public double getAverageGrade() throws NoSuchElementException {
        return this.students.stream()
                .flatMapToDouble(Student::getGradesAsDoubleStream)
                .average()
                .orElse(0.0);
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
