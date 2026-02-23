package Q2;


public class Override2 extends Base {

    Override2(int a, double b) {
        super(a, b);
    }
    //Code for d
    public Override2(Base ob){
        super(ob);
    }

    //Code for e
    
    void show(int a, double b){
        System.out.println("Sum of a,b is: "+ (a+b));
    }
}
