package Q2.M1;

public class Monster {

    String name;
    double weight;

    public Monster(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void increaseWeigh(double weight) {
        System.out.println("New weight of monster = " + (this.weight + weight));
    }

    public boolean eat(Human h) {
        if (h.intelligence == "high") {
            return true;
        } else {
            this.increaseWeigh(weight);
            return false;
        }
    }

    public String scare(boolean bravery) {
        String str;
        if (bravery == false) {
            str = "Human scared";
        } else {
            str = "Human is too brave to scare";
        }
        return str;
    }

}
