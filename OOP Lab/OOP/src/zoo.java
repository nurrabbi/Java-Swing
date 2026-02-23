public class zoo {

    public static void main(String[] arg) {
        Animal a = new Animal();
        Bird b = new Magpie();
        Magpie c = (Magpie) b;
        
        a.fly();
        b.fly();
        ((Magpie)b).fly(15);
        c.fly();
        
        a.eat();
        b.eat();
        c.eat();
    }
}
