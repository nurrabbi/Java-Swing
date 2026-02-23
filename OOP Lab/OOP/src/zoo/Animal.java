package zoo;

public class Animal{
   public String name;
    protected int legs;
    public Animal(String name, int legs){
        this.name=name;
        this.legs=legs;
    }
    void print(){
     System.out.println(name+""+legs);
    }
}