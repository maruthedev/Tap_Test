package model;

import java.util.List;

public class Examinee {
    public final static String TYPE_SCIENCE = "s";
    public final static String TYPE_HUMANITIES = "l";
    private String type;
    private List<Subject> subjects;

    public Examinee() {
    }

    public Examinee(String type, List<Subject> subjects) {
        this.type = type;
        this.subjects = subjects;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getSubjectPoints(String type) {
        int points = 0;
        switch (type) {
            case Subject.TYPE_HUMANITIES:
            case Subject.TYPE_SCIENCE:
            case Subject.TYPE_OTHER:
                for (Subject subject : subjects) {
                    if (subject.getType().equals(type)) {
                        points += subject.getPoint();
                    }
                }
                break;
            default:
                for (Subject subject : subjects) {
                    points += subject.getPoint();
                }
                break;
        }

        return points;
    }
}
