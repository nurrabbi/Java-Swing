/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalPreparation;

/**
 *
 * @author nurho
 */
class Cosumer implements Runnable {

    private Q6_thread_Stack stack;

    public Cosumer(Q6_thread_Stack s) {
        stack = s;
    }

    public void run() {
        char c;
        for (int i = 0; i < 50; i++) {
            c = stack.pop();
            System.out.println(c);
        }
        try {
            Thread.sleep((int) (Math.random() * 300));
        } catch (InterruptedException e) {
            System.out.println("Sleep is not possible...!" + e);
        }
    }

}
