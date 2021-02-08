import java.util.List;
//Test push by chester

public class Student implements Nameable, HasLevel {

    private Level level;

    protected List<Double> grades;
    protected String name;

    public Student() {}

    public Student(List<Double> grades) {
        this.grades = grades;
        this.level = Level.L100;
    }

    public Student(String name, List<Double> grades) {
        this.name = name;
        this.grades = grades;
        this.level = Level.L100;
    }

    public Student(String name, List<Double> grades, Level level) {
        this.name = name;
        this.grades = grades;
        this.level = level;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageGrade() {
        double result = 0.0;

        for (Double grade : grades) {
            result += grade;
        }
        result = result / grades.size();

        return result;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
