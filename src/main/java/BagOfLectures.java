import java.util.ArrayList;

public class BagOfLectures {
    private final ArrayList<Lecture> lectures;

    public BagOfLectures() {
        this.lectures = new ArrayList<>();
    }

    public BagOfLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addLecture(Lecture l) {
        this.lectures.add(l);
    }

    public void removeLecture(Lecture l) {
        this.lectures.remove(l);
    }

    public void clearLectures() {
        this.lectures.clear();
    }
}
