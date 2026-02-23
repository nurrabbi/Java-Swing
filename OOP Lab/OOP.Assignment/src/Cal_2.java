
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import javax.swing.GroupLayout;

class Cal_2 implements WindowListener, ActionListener {

    private Frame f;
    private Panel p1, p2;
    private TextField t1, t2;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b00, bDot, bAC, bPlus, bMin, bStar, bDiv, bPac, bEqual, b20;

    private int[] ar = new int[100];
    private double sum = 0, temp = 0, N1 = 0, N2 = 0;
    private String op = "";

    public Cal_2() {
        f = new Frame("Calculator 203.016");
        p1 = new Panel();
        p2 = new Panel();
        t1 = new TextField(22);
        t2 = new TextField(22);
        b1 = new Button("1");
        b1.setActionCommand("1");
        b2 = new Button("2");
        b2.setActionCommand("2");
        b3 = new Button("3");
        b3.setActionCommand("3");
        b4 = new Button("4");
        b4.setActionCommand("4");
        b5 = new Button("5");
        b5.setActionCommand("5");
        b6 = new Button("6");
        b6.setActionCommand("6");
        b7 = new Button("7");
        b7.setActionCommand("7");
        b8 = new Button("8");
        b8.setActionCommand("8");
        b9 = new Button("9");
        b9.setActionCommand("9");
        b0 = new Button("0");
        b0.setActionCommand("0");
        b00 = new Button("00");
        b00.setActionCommand("00");
        bDot = new Button(".");
        bDot.setActionCommand(".");
        bAC = new Button("Clear");
        bAC.setActionCommand("Clear");
        bPlus = new Button("+");
        bPlus.setActionCommand("+");
        bMin = new Button("-");
        bMin.setActionCommand("-");
        bStar = new Button("*");
        bStar.setActionCommand("*");
        bDiv = new Button("/");
        bDiv.setActionCommand("/");
        bPac = new Button("%");
        bPac.setActionCommand("%");
        bEqual = new Button("=");
        bEqual.setActionCommand("=");
        b20 = new Button();

    }

    public void CreateFrame() {
        f.addWindowListener(this);
        f.setSize(400, 600);
        f.setBackground(Color.yellow);
        f.setLayout(new GridLayout(2, 1));
        p1.setSize(390, 550);
        p1.setLayout(new GridLayout(5, 4));
        p1.setBackground(Color.lightGray);

        p2.setSize(390, 30);
        p2.setLayout(new FlowLayout(10, 10, 10));
        p2.setBackground(Color.lightGray);
        
        t1.setBackground(Color.white);
        t1.setForeground(Color.black);
        t1.setFont(new Font("Serif", Font.BOLD, 30));
        
        t2.setBackground(Color.white);
        t2.setForeground(Color.black);
        t2.setFont(new Font("Serif", Font.BOLD, 30));
        t2.setVisible(false);

        b1.addActionListener(this);
        b1.setBackground(Color.GRAY);
        b1.setForeground(Color.YELLOW);
        b1.setFont(new Font("Serif", Font.BOLD, 30));

        b2.addActionListener(this);
        b2.setBackground(Color.GRAY);
        b2.setForeground(Color.YELLOW);
        b2.setFont(new Font("Serif", Font.BOLD, 30));

        b3.addActionListener(this);
        b3.setBackground(Color.GRAY);
        b3.setForeground(Color.YELLOW);
        b3.setFont(new Font("Serif", Font.BOLD, 30));

        b4.addActionListener(this);
        b4.setBackground(Color.GRAY);
        b4.setForeground(Color.YELLOW);
        b4.setFont(new Font("Serif", Font.BOLD, 30));

        b5.addActionListener(this);
        b5.setBackground(Color.GRAY);
        b5.setForeground(Color.YELLOW);
        b5.setFont(new Font("Serif", Font.BOLD, 30));

        b6.addActionListener(this);
        b6.setBackground(Color.GRAY);
        b6.setForeground(Color.YELLOW);
        b6.setFont(new Font("Serif", Font.BOLD, 30));

        b7.addActionListener(this);
        b7.setBackground(Color.GRAY);
        b7.setForeground(Color.YELLOW);
        b7.setFont(new Font("Serif", Font.BOLD, 30));

        b8.addActionListener(this);
        b8.setBackground(Color.GRAY);
        b8.setForeground(Color.YELLOW);
        b8.setFont(new Font("Serif", Font.BOLD, 30));

        b9.addActionListener(this);
        b9.setBackground(Color.GRAY);
        b9.setForeground(Color.YELLOW);
        b9.setFont(new Font("Serif", Font.BOLD, 30));

        b0.addActionListener(this);
        b0.setBackground(Color.GRAY);
        b0.setForeground(Color.YELLOW);
        b0.setFont(new Font("Serif", Font.BOLD, 30));

        b00.addActionListener(this);
        b00.setBackground(Color.GRAY);
        b00.setForeground(Color.YELLOW);
        b00.setFont(new Font("Serif", Font.BOLD, 30));

        bDot.addActionListener(this);
        bDot.setBackground(Color.GRAY);
        bDot.setForeground(Color.YELLOW);
        bDot.setFont(new Font("Serif", Font.BOLD, 30));

        bAC.addActionListener(this);
        bAC.setBackground(Color.GRAY);
        bAC.setForeground(Color.YELLOW);
        bAC.setFont(new Font("Serif", Font.BOLD, 30));

        bPlus.addActionListener(this);
        bPlus.setBackground(Color.GRAY);
        bPlus.setForeground(Color.YELLOW);
        bPlus.setFont(new Font("Serif", Font.BOLD, 30));

        bMin.addActionListener(this);
        bMin.setBackground(Color.GRAY);
        bMin.setForeground(Color.YELLOW);
        bMin.setFont(new Font("Serif", Font.BOLD, 30));

        bStar.addActionListener(this);
        bStar.setBackground(Color.GRAY);
        bStar.setForeground(Color.YELLOW);
        bStar.setFont(new Font("Serif", Font.BOLD, 30));

        bDiv.addActionListener(this);
        bDiv.setBackground(Color.GRAY);
        bDiv.setForeground(Color.YELLOW);
        bDiv.setFont(new Font("Serif", Font.BOLD, 30));

        bPac.addActionListener(this);
        bPac.setBackground(Color.GRAY);
        bPac.setForeground(Color.YELLOW);
        bPac.setFont(new Font("Serif", Font.BOLD, 30));

        bEqual.addActionListener(this);
        bEqual.setBackground(Color.GRAY);
        bEqual.setForeground(Color.YELLOW);
        bEqual.setFont(new Font("Serif", Font.BOLD, 30));

        b20.addActionListener(this);
        b20.setBackground(Color.GRAY);
        b20.setForeground(Color.YELLOW);
        b20.setFont(new Font("Serif", Font.BOLD, 30));

        p2.add(t1);
        p2.add(t2);
        f.add(p2);
        f.add(p1);

        p1.add(bAC);
        p1.add(b20);
        p1.add(bPac);
        p1.add(bPlus);

        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(bMin);

        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(bStar);

        p1.add(b7);
        p1.add(b8);
        p1.add(b9);
        p1.add(bDiv);

        p1.add(b0);
        p1.add(b00);
        p1.add(bDot);
        p1.add(bEqual);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Cal_2 fp = new Cal_2();
        String str1 = t1.getText();
        String str2 = t2.getText();
        String str3 = null;
        if (e.getActionCommand() == "1") {
            str3 = "1";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "2") {
            str3 = "2";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "3") {
            str3 = "3";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "4") {
            str3 = "4";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "5") {
            str3 = "5";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "6") {
            str3 = "6";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "7") {
            str3 = "7";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "8") {
            str3 = "8";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "9") {
            str3 = "9";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "0") {
            str3 = "0";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "00") {
            str3 = "00";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == ".") {
            str3 = ".";
            t1.setText(str1 + str3);
            t2.setText(str2 + str3);
        } else if (e.getActionCommand() == "+") {
            temp = Double.parseDouble(t2.getText());
            N1 = temp;
            op = "+";
            str3 = "+";
            t1.setText(str1 + str3);
            t2.setText("");
        } else if (e.getActionCommand() == "-") {
            temp = Double.parseDouble(t2.getText());
            N1 = temp;
            op = "-";
            str3 = "-";
            t1.setText(str1 + str3);
            t2.setText("");
        } else if (e.getActionCommand() == "*") {
            temp = Double.parseDouble(t2.getText());
            N1 = temp;
            op = "*";
            str3 = "*";
            t1.setText(str1 + str3);
            t2.setText("");
        } else if (e.getActionCommand() == "/") {
            temp = Double.parseDouble(t2.getText());
            N1 = temp;
            op = "/";
            str3 = "/";
            t1.setText(str1 + str3);
            t2.setText("");
        } else if (e.getActionCommand() == "%") {
            temp = Double.parseDouble(t2.getText());
            N1 = temp;
            op = "%";
            str3 = "%";
            t1.setText(str1 + str3);
            t2.setText("");
        } else if (e.getActionCommand() == "=") {

            if (op == "+") {
                temp = Double.parseDouble(t2.getText());
                N2 = temp;
                sum = N1 + N2;
                t2.setText("");
                t1.setText(str1 + "=" + sum);
            } else if (op == "-") {
                temp = Double.parseDouble(t2.getText());
                N2 = temp;
                sum = N1 - N2;
                t2.setText("");
                t1.setText(str1 + "=" + sum);
            } else if (op == "*") {
                temp = Double.parseDouble(t2.getText());
                N2 = temp;
                sum = N1 * N2;
                t2.setText("");
                t1.setText(str1 + "=" + sum);
            } else if (op == "/") {
                temp = Double.parseDouble(t2.getText());
                N2 = temp;
                sum = N1 / N2;
                t2.setText("");
                t1.setText(str1 + "=" + sum);
            } else if (op == "%") {
                temp = Double.parseDouble(t2.getText());
                N2 = temp;
                sum = (N1 * N2) / 100;
                t2.setText("");
                t1.setText(str1 + "=" + sum);
            }
            N1 = sum;
            t1.setText("");
            t1.setText(str1 + "=" + N1);
            t2.setText("" + N1);
        } else if (e.getActionCommand() == "Clear") {
            t1.setText("");
            t2.setText("");
            sum = 0;
            temp = 0;
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
        Cal_2 fp = new Cal_2();
        fp.CreateFrame();

    }
}
