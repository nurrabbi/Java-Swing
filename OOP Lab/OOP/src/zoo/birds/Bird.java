package zoo.birds;

import zoo.Animal;

class Bird extends Animal {

    String color = "red";

    Bird() {
        super("Magpie", 2);
    }

    Bird(String name, int legs, String color) {
        super(name, legs);
        this.color = color;
    }

    void print() {
        System.out.println(name+" "+legs+" "+color);
    }

}
