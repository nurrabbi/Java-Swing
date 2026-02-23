/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

class Producer implements Runnable {

    private Q6_thread_Stack stack;

    public Producer(Q6_thread_Stack s) {
        stack = s;
    }

    public void run() {
        char c;
        for (int i = 0; i < 50; i++) {
            c = (char) (Math.random() * 26 + 'A');
            stack.push(c);
            System.out.println("Producer: " + c);
            try {
                Thread.sleep((int) (Math.random() * 300));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

}
