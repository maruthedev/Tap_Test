import controller.Handler;
import model.Examinee;
import model.Subject;

import java.util.Scanner;

public class StartApp {
    private final Handler handler;
    private final Scanner scanner;

    StartApp() {
        handler = Handler.getInstance();
        scanner = new Scanner(System.in);
    }

    private void run() {
        this.setPassedConditions();
        this.addSubject();
        this.adjustExisingSubject();
        this.inputDataSet();
    }

    /**
     * Set passed conditions
     */
    private void setPassedConditions() {
        System.out.println("-----------------------");
        System.out.println("|Set passed conditions|");
        System.out.println("-----------------------");
        System.out.println("Set minimum total points: ");
        try {
            Handler.MINIMUM_TOTAL_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Minimum total points is set to default: " + Handler.MINIMUM_TOTAL_POINTS);
        }

        System.out.println("Set minimum science points: ");
        try {
            Handler.MINIMUM_SCIENCE_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("minimum science points is set to default: " + Handler.MINIMUM_SCIENCE_POINTS);
        }

        System.out.println("Set minimum humanities points: ");
        try {
            Handler.MINIMUM_HUMANITIES_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("minimum humanities points is set to default: " + Handler.MINIMUM_HUMANITIES_POINTS);
        }
    }

    /**
     * Additional Subject
     */
    private void addSubject() {
        System.out.println("--------------------");
        System.out.println("|Additional Subject|");
        System.out.println("--------------------");
        System.out.println("Number of additional subjects (current is " + Handler.TOTAL_SUBJECTS + "): ");
        int additionalSubjectNumber;
        try {
            additionalSubjectNumber = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            additionalSubjectNumber = 0;
            System.out.println("NO SUBJECT HAS BEEN ADDED");
        }
        for (int i = 0; i < additionalSubjectNumber; i++) {
            Subject additionalSubject = new Subject();
            System.out.println("Subject Name: ");
            while (additionalSubject.getName() == null) {
                additionalSubject.setName(scanner.nextLine());
            }

            additionalSubject.setId(++Handler.TOTAL_SUBJECTS);
            System.out.println("Subject Type (1-science, 2-humanities, 3-other): ");

            while (additionalSubject.getType() == null) {
                additionalSubject.setType(scanner.nextLine());
            }
            additionalSubject.setPoint(0);
            Handler.getSubjectsInExam().add(additionalSubject);
        }
    }

    /**
     * Adjust existing subjects
     */
    private void adjustExisingSubject() {
        adjust:
        while (true) {
            System.out.println("-------------------------");
            System.out.println("|Adjust Existing Subject|");
            System.out.println("-------------------------");
            for (Subject subject : Handler.getSubjectsInExam()) {
                System.out.println(subject.toString());
            }
            System.out.println("Choose a Subject to adjust, or 'q' to skip");
            String option;
            Subject subject = null;
            chooseSubject:
            while (true) {
                option = scanner.nextLine();
                if (option.equals("q")) break adjust;
                try {
                    for (Subject s : Handler.getSubjectsInExam()) {
                        if (s.getId() != Integer.parseInt(option)) continue;
                        subject = s;
                        break chooseSubject;
                    }
                } catch (Exception e) {
                    System.out.println("There's no subject has id like " + option + ", please try again");
                }
            }

            System.out.println("Subject " + option + " 'a' to adjust, 'd' to delete or 'q' to skip");

            String optionAdjust = scanner.nextLine();
            if (optionAdjust.equals("a")) {
                System.out.println("Change subject Type to (1-science, 2-humanities, 3-other or 'q' to skip): ");
                String optionType = scanner.nextLine();
                if (optionType.equals("q")) break adjust;
                subject.setType(optionType);
                while (subject.getType() == null) {
                    subject.setType(scanner.nextLine());
                }
            } else if (optionAdjust.equals("d")) {
                System.out.println("Delete subject " + subject.getName() + "? y/n");
                String optionDelete = scanner.nextLine();
                if (optionDelete.equals("y")) {
                    Handler.getSubjectsInExam().remove(subject);
                    --Handler.TOTAL_SUBJECTS;
                }
            }
        }
    }

    /**
     * Input dataset
     */
    private void inputDataSet() {
        System.out.println("---------------");
        System.out.println("|Input dataset|");
        System.out.println("---------------");
        int N = Integer.parseInt(scanner.nextLine());
        while (N-- > 0) {
            Examinee examinee = null;
            while (examinee == null) {
                examinee = handler.stringToExaminee(scanner.nextLine());
            }
            if (handler.examine(examinee)) Handler.PASSED_EXAMINEES++;
        }
        System.out.println(Handler.PASSED_EXAMINEES);
    }

    public static void main(String[] args) {
        StartApp app = new StartApp();
        app.run();
    }


}

/*
5
s 70 78 82 57 74
l 68 81 81 60 78
s 63 76 55 80 75
s 90 100 96 10 10
l 88 78 81 97 93

5
s 70 78 82 57 74 -50
l 68 81 81 60 78 -30
s 63 76 55 80 75 -50
s 90 100 96 10 10 -50
l 88 78 81 97 93 50

20
l 100 67 39 85 87
s 38 75 75 45 90
l 43 95 7 35 49
l 82 77 74 35 44
s 96 80 92 58 84
l 23 60 44 27 3
l 42 24 52 23 63
s 44 78 98 51 10
l 93 38 73 88 12
l 34 29 43 48 61
l 83 33 97 3 59
l 24 84 22 35 33
s 81 42 80 34 87
l 8 87 82 80 100
l 48 75 75 3 50
l 93 76 25 71 31
s 60 92 64 66 11
l 61 47 6 21 83
l 68 1 47 81 78
l 8 72 54 20 25
* */