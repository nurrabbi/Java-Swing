public class Manager {

    private int Salary;
    private String Address;

    public void setter(int Salary, String Address) {
        this.Salary = Salary;
        this.Address = Address;
    }

    public int getterInt() {
        return Salary;
    }

    public String getterString() {
        return Address;
    }
    public static void main(String [] args){
        Manager m = new Manager();
        m.setter(5000,"UIU");
        int x = m.getterInt();
        String y = m.getterString();
        System.out.println("Salary: "+ x);
        System.out.println("Address: "+ y);
    }
}
