import junit.framework.TestCase;

import java.util.Arrays;

public class LectureTest extends TestCase {
    Lecture lecture;

    public void setUp() throws Exception {
        super.setUp();

        lecture = new Lecture();
    }

    public void testEnter() {

        assertEquals(0, lecture.getStudents().size());
        lecture.enter(new Student(Arrays.asList(1.0, 2.0, 3.0)));

        assertEquals(1, lecture.getStudents().size());

    }

    public void testGetHighestAverageGrade() {
        Student s1 = new Student(Arrays.asList(1.0, 2.0, 3.0));  // 2.0
        Student s2 = new Student(Arrays.asList(4.0, 5.0, 6.0));  // 5.0
        lecture.enter(s1);
        lecture.enter(s2);

        assertEquals(5.0, lecture.getHighestAverageGrade());
    }

    public void testNaughtyStudentsScoringHigher() {
        var ns1 = new NaughtyStudent(Arrays.asList(1.0, 2.0, 3.0));  // 2.0
        var ns2 = new NaughtyStudent(Arrays.asList(4.0, 5.0, 6.0));  // 5.0
        var ns3 = new NaughtyStudent(Arrays.asList(7.0, 8.0, 9.0));  // 8.0

        assertTrue(ns1.getAverageGrade() > 2.0);
        assertTrue(ns2.getAverageGrade() > 5.0);
        assertTrue(ns3.getAverageGrade() > 8.0);

    }

}
