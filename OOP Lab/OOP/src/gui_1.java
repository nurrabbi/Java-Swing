/*
    How to use WindowListener, Frame, Panel
*/

import java.awt.*;
import java.awt.event.*;

class FrameWithPanel implements WindowListener {
    private Frame f;
    private Panel p;

    public FrameWithPanel() {
        f = new Frame("Frame Title");
        p = new Panel();
    }

    public void launchFrame() {
        f.addWindowListener(this);
        f.setSize(400, 400);
        f.setBackground(Color.red);
        f.setLayout(null);

        p.setSize(150, 150);
        p.setBackground(Color.green);
        f.add(p);
        f.setVisible(true);
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
        FrameWithPanel fp = new FrameWithPanel();
        fp.launchFrame();
    }
}
