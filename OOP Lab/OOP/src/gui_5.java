
import java.awt.*;
import java.awt.event.*;

class TestColors implements WindowListener, ActionListener {

    private Frame f;
    private Button b;

    public TestColors() {
        f = new Frame("Frame Title");
        b = new Button("Change Color");
        b.setActionCommand("button press");
    }

    public void launchFrame() {
        b.addActionListener(this);
        b.setForeground(Color.red);
        b.setBackground(Color.yellow);
        f.add(b);
        f.addWindowListener(this);
        f.setSize(300, 300);
        f.setBackground(Color.green);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int x, y, z;
        if (e.getActionCommand() == "button press") {
            x = (int) (Math.random() * 100);
            y = (int) (Math.random() * 100);
            z = (int) (Math.random() * 100);
            Color c = new Color(x, y, z);
            f.setBackground(c);
        }
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
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
        TestColors tc = new TestColors();
        tc.launchFrame();
    }
}
