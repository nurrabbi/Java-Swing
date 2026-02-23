package Q2.M1;

public class Human {

    private int id;
    String intelligence;
    protected boolean bravery;

    {
        System.out.println("To avoid getting scared or eaten be brave or intellient.");
    }

    public Human(int id, String intelligence, boolean bravery) {
        this.id = id;
        this.intelligence = intelligence;
        this.bravery = bravery;
    }

    public int getid() {
        return id;
    }

    public boolean getBravery() {
        return bravery;
    }
}
