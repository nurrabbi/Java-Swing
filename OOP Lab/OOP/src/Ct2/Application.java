package Ct2;

public class Application{
    
    public static void main(String[] args){
        Course c1 = new Course("OOP","CSI1211", 3);
        Course c2 = new Course("SPL","CSI1121",1);
        c1.display();
        c2.display();
        c1.updateCourse(c2);
        c1.display();
        c2.display();
    }
}