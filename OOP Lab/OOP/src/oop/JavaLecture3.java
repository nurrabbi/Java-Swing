package oop;

public class JavaLecture3 {
//instence Variable
    public int x, y, z;
//Constructor Access Modifire.
    public JavaLecture3(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int sum() {
        return x + y + z;
    }

    {
        //Block Declearation
        System.out.println("Hello!!!!!!, I am Block");
    }

    public static void main(String[] args) {
        JavaLecture3 obj = new JavaLecture3(100, 200, 300);
      //  sum op = new sum();
        System.out.println("X = "+ obj.x + "; Y = " + obj.y + "; Z = " + obj.z );
        System.out.println("Sum = "+obj.sum());
    }
}
