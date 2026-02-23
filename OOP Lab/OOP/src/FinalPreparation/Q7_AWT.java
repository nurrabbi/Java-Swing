package FinalPreparation;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Q7_AWT implements WindowListener, ActionListener {

    private Frame f;
    private Panel p1, p2;
    private Button b1, b2, b3;
    private Label lbl, lbl2;
    private TextField t1, t2;


    public Q7_AWT() {
        f = new Frame("");
        p1 = new Panel();
        lbl = new Label("Label 1");
        lbl2 = new Label("Label 2");
        t1 = new TextField("Text 1");
        t2 = new TextField("Text 2");
        b1 = new Button("1");
        b1.setActionCommand("1");
        b2 = new Button("2");
        b2.setActionCommand("2");
        b3 = new Button("3");
        b3.setActionCommand("3");

    }

    public void Design() {

        f.addWindowListener(this);
        f.setSize(310, 320);
        f.setBackground(Color.GREEN);
        f.setLayout(new BorderLayout());
        f.setVisible(true);

        p1.setSize(300, 300);
        p1.setLayout(new GridLayout(3, 1));
        p1.setBackground(Color.lightGray);

        lbl.setBackground(Color.white);
        lbl.setForeground(Color.black);
        lbl.setFont(new Font("Serif", Font.BOLD, 30));

        lbl2.setBackground(Color.white);
        lbl2.setForeground(Color.black);
        lbl2.setFont(new Font("Serif", Font.BOLD, 30));

        t1.setBackground(Color.white);
        t1.setForeground(Color.black);
        t1.setFont(new Font("Serif", Font.BOLD, 30));

        t2.setBackground(Color.white);
        t2.setForeground(Color.black);
        t2.setFont(new Font("Serif", Font.BOLD, 30));

        b1.addActionListener(this);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Serif", Font.BOLD, 30));

        b2.addActionListener(this);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Serif", Font.BOLD, 30));

        b3.addActionListener(this);
        b3.setBackground(Color.WHITE);
        b3.setForeground(Color.BLACK);
        b3.setFont(new Font("Serif", Font.BOLD, 30));

        f.add(p1, BorderLayout.CENTER);
        f.add(t1, BorderLayout.NORTH);
        f.add(t2, BorderLayout.SOUTH);
        f.add(b1, BorderLayout.EAST);
        f.add(b2, BorderLayout.WEST);

        p1.add(lbl);
        p1.add(b3);
        p1.add(lbl2);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int x, y, z;
        x = (int) (Math.random() * 100);
        y = (int) (Math.random() * 100);
        z = (int) (Math.random() * 100);
        Color c = new Color(x, y, z);

        if (e.getActionCommand() == "1") {
            lbl.setBackground(c);
            lbl.setText("Button 1 pressed");
        } else if (e.getActionCommand() == "2") {
            lbl2.setBackground(c);
            lbl2.setText("Button 2 pressed");
        } else if (e.getActionCommand() == "3") {
            lbl.setBackground(Color.white);
            lbl.setText("Label 3");
            lbl2.setBackground(Color.white);
            lbl2.setText("Label 3");
        }

    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
        f.setLocationRelativeTo(null);
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public static void main(String[] args) {
        Q7_AWT awt = new Q7_AWT();
        awt.Design();

    }
}
