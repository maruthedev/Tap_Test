import controller.Handler;
import model.Subject;

import java.util.List;
import java.util.Scanner;

public class StartApp {

    public static void main(String[] args) {
        Handler handler = Handler.getInstance();
        Scanner scanner = new Scanner(System.in);

        /**
         * Set passed conditions
         */
        System.out.println("-----------------------");
        System.out.println("|Set passed conditions|");
        System.out.println("-----------------------");
        System.out.println("Set minimum total points: ");
        try {
            Handler.MINIMUM_TOTAL_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("minimum total points is set to default");
        }

        System.out.println("Set minimum science points: ");
        try {
            Handler.MINIMUM_SCIENCE_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("minimum science points is set to default");
        }

        System.out.println("Set minimum humanities points: ");
        try {
            Handler.MINIMUM_HUMANITIES_POINTS = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("minimum humanities points is set to default");
        }


        /**
         * Additional Subject
         */
        System.out.println("--------------------");
        System.out.println("|Additional Subject|");
        System.out.println("--------------------");
        System.out.println("Number of additional subjects (current is " + Handler.TOTAL_SUBJECTS + "): ");
        int additionalSubjectNumber = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < additionalSubjectNumber; i++) {
            Subject additionalSubject = new Subject();
            System.out.println("Subject Name: ");
            scanner.nextLine();
            additionalSubject.setName(++Handler.TOTAL_SUBJECTS);
            System.out.println("Subject Type (1-science, 2-humanities, 3-other): ");
            String type = scanner.nextLine();
            try {
                int typeN = Integer.parseInt(type);
                switch (typeN) {
                    case 1:
                        additionalSubject.setType(Subject.TYPE_SCIENCE);
                        break;
                    case 2:
                        additionalSubject.setType(Subject.TYPE_HUMANITIES);
                        break;
                    case 3:
                        additionalSubject.setType(Subject.TYPE_OTHER);
                        break;
                }
            } catch (Exception e) {
                switch (type) {
                    case Subject.TYPE_SCIENCE:
                        additionalSubject.setType(Subject.TYPE_SCIENCE);
                        break;
                    case Subject.TYPE_HUMANITIES:
                        additionalSubject.setType(Subject.TYPE_HUMANITIES);
                        break;
                    case Subject.TYPE_OTHER:
                        additionalSubject.setType(Subject.TYPE_OTHER);
                        break;
                }
            }

            additionalSubject.setPoint(0);
            Handler.getSubjectsInExam().add(additionalSubject);
        }

        /**
         * Input dataset
         */
        System.out.println("---------------");
        System.out.println("|Input dataset|");
        System.out.println("---------------");
        int N = Integer.parseInt(scanner.nextLine());
        while (N-- > 0) {
            String input = scanner.nextLine();
            if (handler.examine(handler.stringToExaminee(input))) {
                Handler.PASSED_EXAMINEES++;
            }
        }
        System.out.println(Handler.PASSED_EXAMINEES);

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