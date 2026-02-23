package Q6;

public class Cake {

    protected String name;
    protected double rate;

    public Cake(String n, double r) {
        name = n;
        rate = r;
    }

    public double getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public double calcPrice() {
        System.out.println("Print the calculated pric.");
        return 0;
    }

    public void printDetails() {
        System.out.println("Print the details.");
    }
}
