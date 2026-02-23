package FinalPreparation;

public class StackTest {

    public static void main(String[] args) {
        Q6_thread_Stack s = new Q6_thread_Stack();

        Producer p = new Producer(s);
        Thread t1 = new Thread(p);
        t1.start();
        Cosumer c = new Cosumer(s);
        Thread t2 = new Thread(c);
        t2.start();
    }
}
