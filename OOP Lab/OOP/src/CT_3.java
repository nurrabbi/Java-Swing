public class CT_3 {

    int a = 100, b = 200, c = 300, sum;
    
    public void Sum(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        sum = a + b + c + sum;
    }
    public void print(){
        sum = a + b + c + sum;
        System.out.println("Sum is:"+sum);
    }
    public static void main(String [] args){
        CT_3 c = new CT_3();
        c.Sum(200,200, (int) 310.5F);
        c.print();
    }
}
