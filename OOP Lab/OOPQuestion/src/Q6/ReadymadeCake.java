package Q6;

public class ReadymadeCake extends Cake {

    int quantity;

    public ReadymadeCake(String n, double r, int q) {
        super(n, r);
        quantity = q;
    }

    public double calcPrice() {
        double total = super.getRate() * quantity;
        return total;
    }

    public void printDetails() {
        System.out.println("Name: " + super.getName() + "/nRate: " + super.getRate() + "/Quantity: " + quantity + "/Total Price: " + this.calcPrice());
    }
}
