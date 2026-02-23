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
class Q6_thread_Stack {

    private int index = 0;
    private char[] data = new char[5];

    public synchronized void push(char c) {
        this.notify();
        if (index != 5) {
            data[index] = c;
            index++;
        }
    }

    public synchronized char pop() {
        this.notify();
        if (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        index--;
        return data[index];
    }

}
