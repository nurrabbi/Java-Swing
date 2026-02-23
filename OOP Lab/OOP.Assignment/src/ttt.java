
import java.awt.*;
import java.awt.event.*;


class ttt implements WindowListener, ActionListener {

    private Frame f;
    private Panel p1;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bClr;
    private int x = 1;

    public ttt() {
        f = new Frame("");
        p1 = new Panel();
        b1 = new Button();
        b1.setActionCommand("1");
        b2 = new Button();
        b2.setActionCommand("2");
        b3 = new Button();
        b3.setActionCommand("3");
        b4 = new Button();
        b4.setActionCommand("4");
        b5 = new Button();
        b5.setActionCommand("5");
        b6 = new Button();
        b6.setActionCommand("6");
        b7 = new Button();
        b7.setActionCommand("7");
        b8 = new Button();
        b8.setActionCommand("8");
        b9 = new Button();
        b9.setActionCommand("9");
        bClr = new Button("Clear");
        bClr.setActionCommand("Clear");

    }

    public void Design() {
        
        f.addWindowListener(this);
        f.setSize(310, 320);
        f.setBackground(Color.GREEN);
        f.setLayout(new BorderLayout());
        
        p1.setSize(300, 300);
        p1.setLayout(new GridLayout(3, 3));
        p1.setBackground(Color.lightGray);

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

        b4.addActionListener(this);
        b4.setBackground(Color.WHITE);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Serif", Font.BOLD, 30));

        b5.addActionListener(this);
        b5.setBackground(Color.WHITE);
        b5.setForeground(Color.BLACK);
        b5.setFont(new Font("Serif", Font.BOLD, 30));

        b6.addActionListener(this);
        b6.setBackground(Color.WHITE);
        b6.setForeground(Color.BLACK);
        b6.setFont(new Font("Serif", Font.BOLD, 30));

        b7.addActionListener(this);
        b7.setBackground(Color.WHITE);
        b7.setForeground(Color.BLACK);
        b7.setFont(new Font("Serif", Font.BOLD, 30));

        b8.addActionListener(this);
        b8.setBackground(Color.WHITE);
        b8.setForeground(Color.BLACK);
        b8.setFont(new Font("Serif", Font.BOLD, 30));

        b9.addActionListener(this);
        b9.setBackground(Color.WHITE);
        b9.setForeground(Color.BLACK);
        b9.setFont(new Font("Serif", Font.BOLD, 30));

        bClr.addActionListener(this);
        bClr.setBackground(Color.WHITE);
        bClr.setForeground(Color.BLACK);
        bClr.setFont(new Font("Serif", Font.BOLD, 30));

        f.add(p1, BorderLayout.CENTER);
        f.add(bClr, BorderLayout.SOUTH);

        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(b7);
        p1.add(b8);
        p1.add(b9);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() != null && x == 0) {
            x = 1;
        } else if (e.getActionCommand() != null && x == 1) {
            x = 0;
        }

        if (e.getActionCommand() == "1") {
            if (x == 0) {
                b1.setLabel("+");
            } else {
                b1.setLabel("o");
            }

        } else if (e.getActionCommand() == "2") {
            if (x == 0) {
                b2.setLabel("+");
            } else {
                b2.setLabel("o");
            }

        } else if (e.getActionCommand() == "3") {
            if (x == 0) {
                b3.setLabel("+");
            } else {
                b3.setLabel("o");
            }

        } else if (e.getActionCommand() == "4") {
            if (x == 0) {
                b4.setLabel("+");
            } else {
                b4.setLabel("o");
            }

        } else if (e.getActionCommand() == "5") {
            if (x == 0) {
                b5.setLabel("+");
            } else {
                b5.setLabel("o");
            }

        } else if (e.getActionCommand() == "6") {
            if (x == 0) {
                b6.setLabel("+");
            } else {
                b6.setLabel("o");
            }

        } else if (e.getActionCommand() == "7") {
            if (x == 0) {
                b7.setLabel("+");
            } else {
                b7.setLabel("o");
            }

        } else if (e.getActionCommand() == "8") {
            if (x == 0) {
                b8.setLabel("+");
            } else {
                b8.setLabel("o");
            }

        } else if (e.getActionCommand() == "9") {
            if (x == 0) {
                b9.setLabel("+");
            } else {
                b9.setLabel("o");
            }

        } else if (e.getActionCommand() == "Clear") {
            b1.setLabel("");
            b2.setLabel("");
            b3.setLabel("");
            b4.setLabel("");
            b5.setLabel("");
            b6.setLabel("");
            b7.setLabel("");
            b8.setLabel("");
            b9.setLabel("");
            x = 1;
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
        ttt fp = new ttt();
        fp.Design();

    }
}

