
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.util.Arrays;

class TicTacToe implements WindowListener, ActionListener {

    private Frame f;
    private Panel p1;
    private Label lbl, lbl2;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bClr;

    private int op = 1, N1, N2, P1, P2;

    public TicTacToe() {
        f = new Frame("TicTacToe 203.016");
        p1 = new Panel();
        lbl = new Label("TicTacToe");
        lbl2 = new Label("Play");
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
        bClr = new Button("C");
        bClr.setActionCommand("Clear");

    }

    public void CreateFrame() {
        f.addWindowListener(this);
        f.setSize(310, 320);
        f.setBackground(Color.GREEN);
        f.setLayout(new BorderLayout());
        
        p1.setSize(300, 300);
        p1.setLayout(new GridLayout(3, 3));
        p1.setBackground(Color.lightGray);
        
        lbl.setBackground(Color.WHITE);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(new Font("Serif", Font.BOLD, 30));

        lbl2.setBackground(Color.WHITE);
        lbl2.setForeground(Color.BLACK);
        lbl2.setFont(new Font("Serif", Font.BOLD, 30));

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
        bClr.setBackground(Color.LIGHT_GRAY);
        bClr.setForeground(Color.BLACK);
        bClr.setFont(new Font("Serif", Font.BOLD, 30));

        f.add(lbl, BorderLayout.NORTH);
        f.add(p1, BorderLayout.CENTER);
        f.add(lbl2, BorderLayout.SOUTH);
        f.add(bClr, BorderLayout.EAST);

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

    public void check(int x, int y) {
        System.out.println("Enter");
        int[] arr1 = new int[3];
        for (int i = 2; i >= 0; i--) {
            int temp = 0;
            temp = x % 10;
            x /= 10;
            arr1[i] = temp;
            System.out.println(arr1[i]);
        }
        System.out.println("new");
        Arrays.sort(arr1);
        for (int j : arr1) {
            System.out.println(j);
            P1 = (P1 * 10) + j;
        }
        int[] arr2 = new int[3];
        for (int i = 2; i >= 0; i--) {
            int temp = 0;
            temp = y % 10;
            y /= 10;
            arr2[i] = temp;
            System.out.println(arr2[i]);
        }
        System.out.println("new");
        Arrays.sort(arr2);
        for (int k : arr2) {
            System.out.println(k);
            P2 = (P2 * 10) + k;
        }
        System.out.println("P1: " + P1);
        System.out.println("P2:" + P2);
        if (P1 == 123 || P1 == 147 || P1 == 159 || P1 == 258 || P1 == 357 || P1 == 369 || P1 == 456 || P1 == 789) {
            lbl2.setText("First Player win");
            System.out.println("First Player win");
        } else if (P2 == 123 || P2 == 147 || P2 == 159 || P2 == 258 || P2 == 357 || P2 == 369 || P2 == 456 || P2 == 789) {
            lbl2.setText("Second Player win");
            System.out.println("Second Player win");
        }

    }

    public void actionPerformed(ActionEvent e) {
        TicTacToe TTT = new TicTacToe();
        if (e.getActionCommand() != null && op == 0) {
            op = 1;
        } else if (e.getActionCommand() != null && op == 1) {
            op = 0;
        }

        if (e.getActionCommand() == "1") {
            if (op == 0) {
                b1.setLabel("+");
                N1 = (N1 * 10) + 1;
            } else {
                b1.setLabel("o");
                N2 = (N2 * 10) + 1;
            }

        } else if (e.getActionCommand() == "2") {
            if (op == 0) {
                b2.setLabel("+");
                N1 = (N1 * 10) + 2;
            } else {
                b2.setLabel("o");
                N2 = (N2 * 10) + 2;
            }

        } else if (e.getActionCommand() == "3") {
            if (op == 0) {
                b3.setLabel("+");
                N1 = (N1 * 10) + 3;
            } else {
                b3.setLabel("o");
                N2 = (N2 * 10) + 3;
            }

        } else if (e.getActionCommand() == "4") {
            if (op == 0) {
                b4.setLabel("+");
                N1 = (N1 * 10) + 4;
            } else {
                b4.setLabel("o");
                N2 = (N2 * 10) + 4;
            }

        } else if (e.getActionCommand() == "5") {
            if (op == 0) {
                b5.setLabel("+");
                N1 = (N1 * 10) + 5;
            } else {
                b5.setLabel("o");
                N2 = (N2 * 10) + 5;
            }

        } else if (e.getActionCommand() == "6") {
            if (op == 0) {
                b6.setLabel("+");
                N1 = (N1 * 10) + 6;
            } else {
                b6.setLabel("o");
                N2 = (N2 * 10) + 6;
            }

        } else if (e.getActionCommand() == "7") {
            if (op == 0) {
                b7.setLabel("+");
                N1 = (N1 * 10) + 7;
            } else {
                b7.setLabel("o");
                N2 = (N2 * 10) + 7;
            }

        } else if (e.getActionCommand() == "8") {
            if (op == 0) {
                b8.setLabel("+");
                N1 = (N1 * 10) + 8;
            } else {
                b8.setLabel("o");
                N2 = (N2 * 10) + 8;
            }

        } else if (e.getActionCommand() == "9") {
            if (op == 0) {
                b9.setLabel("+");
                N1 = (N1 * 10) + 9;
            } else {
                b9.setLabel("o");
                N2 = (N2 * 10) + 9;
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
            N1 = 0;
            N2 = 0;
            P1 = 0;
            P2 = 0;
            op = 1;
        }

        if (N1 > 122) {
            TTT.check(N1, N2);
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
        TicTacToe fp = new TicTacToe();
        fp.CreateFrame();

    }
}
