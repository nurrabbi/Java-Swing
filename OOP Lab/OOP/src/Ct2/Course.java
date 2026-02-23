package Ct2;

public class Course {

    String name, id;
    int credit;

    public Course(String name, String id, int credit) {
        this.name = name;
        this.id = id;
        this.credit = credit;
    }

    public void display() {
        System.out.printf("%s - %s - %d\n", name, id, credit);
    }

    public void updateCourse(Course c1) {
        this.name = c1.name;
        this.id = c1.id;
        c1.credit--;
    }
}
