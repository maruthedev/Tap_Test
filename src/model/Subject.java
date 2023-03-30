package model;

import controller.Handler;

public class Subject {
    public static final String TYPE_SCIENCE = "science";
    public static final String TYPE_HUMANITIES = "humanities";
    public static final String TYPE_OTHER = "other";
    public static final int ENGLISH = 1;
    public static final int MATH = 2;
    public static final int SCIENCE = 3;
    public static final int JAPANESE = 4;
    public static final int GEO_HIS = 5;

    private int id;
    private String type;
    private int point;
    private String name;

    public Subject() {

    }

    public Subject(int id, String type, int point, String name) {
        this.id = id;
        this.type = type;
        this.point = point;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        for(Subject subject : Handler.getSubjectsInExam()){
            if(subject.getName().equalsIgnoreCase(name)){
                this.name = null;
                System.out.println("There is already a subject with the name " + name + ", please try again");
                return;
            }
        }
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        switch (type) {
            case "1":
            case Subject.TYPE_SCIENCE:
                this.type = Subject.TYPE_SCIENCE;
                break;
            case "2":
            case Subject.TYPE_HUMANITIES:
                this.type = Subject.TYPE_HUMANITIES;
                break;
            case "3":
            case Subject.TYPE_OTHER:
                this.type = Subject.TYPE_OTHER;
                break;
            default:
                System.out.println("There's no subject type like '" + type + "', please input again");
                this.type = null;
                break;
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "id: " + id + "name: " + name + ", type:" + type;
    }
}
