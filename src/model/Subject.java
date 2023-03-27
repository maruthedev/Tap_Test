package model;

public class Subject {
    public static final String TYPE_SCIENCE = "science";
    public static final String TYPE_HUMANITIES = "humanities";
    public static final String TYPE_OTHER = "other";
    public static final int ENGLISH = 1;
    public static final int MATH = 2;
    public static final int SCIENCE = 3;
    public static final int JAPANESE = 4;
    public static final int GEO_HIS = 5;

    private int name;
    private String type;
    private int point;

    public Subject(){

    }

    public Subject(int name, String type, int point){
        this.name = name;
        this.type = type;
        this.point = point;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
