package model;

import java.util.ArrayList;
import java.util.List;

public class Handler {
    public static final int MINIMUM_POINTS = 350;
    public static final int MINIMUM_SCIENCE_POINTS = 160;
    public static final int MINIMUM_HUMANITIES_POINTS = 160;
    public static final int TOTAL_SUBJECTS = 5;
    public static int PASSED_EXAMINEES = 0;
    private static Handler Instance;

    public static Handler getInstance() {
        if (Instance == null) {
            Instance = new Handler();
        }
        return Instance;
    }

    private Handler() {

    }

    public Examinee stringToExaminee(String s) {
        Examinee examinee = new Examinee();
        List<Subject> subjects = new ArrayList<Subject>();
        String[] tokens;
        try {
            tokens = s.split(" ");
        } catch (Exception e) {
            System.out.println("NO MORE DATA");
            return null;
        }

        try {
            if (tokens[0].equals(Examinee.TYPE_HUMANITIES) || tokens[0].equals(Examinee.TYPE_SCIENCE)) {
                examinee.setType(tokens[0]);
            }
        } catch (Exception e) {
            System.out.println("NO TYPE OF EXAMINEE LIKE " + tokens[0]);
            return null;
        }

        for (int i = 1; i <= Handler.TOTAL_SUBJECTS; i++) {
            Subject subject = null;
            switch (i) {
                case Subject.ENGLISH:
                    subject = new Subject(Subject.ENGLISH, Subject.TYPE_OTHER, Integer.parseInt(tokens[i]));
                    break;
                case Subject.MATH:
                    subject = new Subject(Subject.MATH, Subject.TYPE_SCIENCE, Integer.parseInt(tokens[i]));
                    break;
                case Subject.SCIENCE:
                    subject = new Subject(Subject.SCIENCE, Subject.TYPE_SCIENCE, Integer.parseInt(tokens[i]));
                    break;
                case Subject.JAPANESE:
                    subject = new Subject(Subject.JAPANESE, Subject.TYPE_HUMANITIES, Integer.parseInt(tokens[i]));
                    break;
                case Subject.GEO_HIS:
                    subject = new Subject(Subject.GEO_HIS, Subject.TYPE_HUMANITIES, Integer.parseInt(tokens[i]));
                    break;
            }
            subjects.add(subject);
        }

        examinee.setSubjects(subjects);

        return examinee;
    }

    public boolean examine(Examinee examinee) {
        if (examinee.getSubjectPoints("") < Handler.MINIMUM_POINTS) return false;
        switch (examinee.getType()) {
            case Examinee.TYPE_HUMANITIES:
                return examinee.getSubjectPoints(Subject.TYPE_HUMANITIES) >= Handler.MINIMUM_HUMANITIES_POINTS;
            case Examinee.TYPE_SCIENCE:
                return examinee.getSubjectPoints(Subject.TYPE_SCIENCE) >= Handler.MINIMUM_SCIENCE_POINTS;
        }
        return false;
    }
}
