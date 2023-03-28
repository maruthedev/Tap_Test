package controller;

import model.Examinee;
import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Handler {
    public static int MINIMUM_TOTAL_POINTS = 350;
    public static int MINIMUM_SCIENCE_POINTS = 160;
    public static int MINIMUM_HUMANITIES_POINTS = 160;
    public static int TOTAL_SUBJECTS = 5;
    public static int PASSED_EXAMINEES = 0;
    private static Handler Instance;
    private static List<Subject> subjectsInExam;

    public static Handler getInstance() {
        if (Instance == null) {
            Instance = new Handler();
        }
        return Instance;
    }

    private Handler() {
        subjectsInExam = new ArrayList<Subject>();
        for (int i =  1; i <= Handler.TOTAL_SUBJECTS; i++) {
            Subject subject = null;
            switch (i) {
                case Subject.ENGLISH:
                    subject = new Subject(Subject.ENGLISH, Subject.TYPE_OTHER, 0);
                    break;
                case Subject.MATH:
                    subject = new Subject(Subject.MATH, Subject.TYPE_SCIENCE, 0);
                    break;
                case Subject.SCIENCE:
                    subject = new Subject(Subject.SCIENCE, Subject.TYPE_SCIENCE, 0);
                    break;
                case Subject.JAPANESE:
                    subject = new Subject(Subject.JAPANESE, Subject.TYPE_HUMANITIES, 0);
                    break;
                case Subject.GEO_HIS:
                    subject = new Subject(Subject.GEO_HIS, Subject.TYPE_HUMANITIES, 0);
                    break;
            }
            subjectsInExam.add(subject);
        }
    }

    public Examinee stringToExaminee(String s) {
        Examinee examinee = new Examinee();
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
            switch (i) {
                case Subject.ENGLISH:
                    subjectsInExam.get(Subject.ENGLISH - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
                case Subject.MATH:
                    subjectsInExam.get(Subject.MATH - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
                case Subject.SCIENCE:
                    subjectsInExam.get(Subject.SCIENCE - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
                case Subject.JAPANESE:
                    subjectsInExam.get(Subject.JAPANESE - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
                case Subject.GEO_HIS:
                    subjectsInExam.get(Subject.GEO_HIS - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
                default:
                    subjectsInExam.get(i - 1).setPoint(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        examinee.setSubjects(subjectsInExam);

        return examinee;
    }

    public boolean examine(Examinee examinee) {
        if (examinee.getSubjectPoints("") < Handler.MINIMUM_TOTAL_POINTS) return false;
        switch (examinee.getType()) {
            case Examinee.TYPE_HUMANITIES:
                return examinee.getSubjectPoints(Subject.TYPE_HUMANITIES) >= Handler.MINIMUM_HUMANITIES_POINTS;
            case Examinee.TYPE_SCIENCE:
                return examinee.getSubjectPoints(Subject.TYPE_SCIENCE) >= Handler.MINIMUM_SCIENCE_POINTS;
        }
        return false;
    }

    public static List<Subject> getSubjectsInExam() {
        return subjectsInExam;
    }
}
