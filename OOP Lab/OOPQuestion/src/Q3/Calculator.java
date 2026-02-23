package Q3;

public class Calculator {

    public int a;
    public int b;

    Calculator(int firstnusdmber, int secondNumber) {
        this.a = firstnusdmber;
        this.b = secondNumber;
    }
    public int sum(){
        return a+b;
    }
    public int subtract(){
        return a-b;
    }
}
