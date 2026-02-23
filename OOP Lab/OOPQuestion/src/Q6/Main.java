package Q6;

public class Main {

    public static void main(String[] args) {
        Cake cake[] = {} ;
        cake[0] = new OrderCake("OrderCake", 150, 3);
        cake[1] = new OrderCake("Chocolate", 200, 5);
        cake[2] = new ReadymadeCake("OrderCake", 200, 2);
        
        for (int i = 0; i < cake.length; i++) {
            cake[i].printDetails();
        }
    }
}
