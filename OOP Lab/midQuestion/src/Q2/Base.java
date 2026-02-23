package Q2;


class Base {

    public int a;
    double b;

    Base(int a, double b) {
        this.a = a;
        this.b = b;
    }
    Base (Base ob){
        a = ob.a;
        b = ob.b;
    }
    // code for a
    
    public void setB(double b){
        double x = b;
    }
    public double b(){
        return b;
    }

    void show() {
        System.out.println("Sum of variables" + "In the Base class " + (a+b));

    }
}
