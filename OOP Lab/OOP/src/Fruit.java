
public class Fruit {

    String name;
    double weight;
    float price, total;

    Fruit(String name, double weight, float price) {
        this.name = name;
        this.weight=weight;
        this.price =price;
    }
    void reducePricePerKG(int x){
        total = (float) ((price-x)*weight);
    }
    void increasePricePerKG(int y){
        total =(float) ((price+y)*weight);
    }
    void printDetails(){
        System.out.printf("Fruit Details:\nName: %s\nWeight: %f\nPrice per kg: %f\nTotal price:%f\n",name,weight,price,total);
    }
}
