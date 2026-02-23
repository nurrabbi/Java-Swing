package Q6;

public class OrderCake extends Cake {

    double weight;

    public OrderCake(String n, double r, double w) {
        super(n, r);
        weight = w;
    }

    public double calcPrice() {
        double total = super.getRate() * weight;
        return total;
    }

    public void printDetails() {
        System.out.println("Name: " + super.getName() + "/nRate: " + super.getRate() + "/Weight: " + weight + "/Total Price: " + this.calcPrice());
    }
}
