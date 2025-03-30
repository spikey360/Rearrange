/*
 * Console.java
 *
 * Created on August 21, 2007, 9:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rearrangeword.swing;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Riju M
 */
public class Console {

    private JFrame frame;
    private int w, h;
    private JPanel panel;

    /**
     * Creates a new instance of this class which displays a JPanel on screen
     * @param panelInstance The panel required to be displayed
     * @param width The width of the panel
     * @param height The height of the panel
     */
    public Console(JPanel panelInstance, int width, int height) {
        panel = panelInstance;
        w = width;
        h = height;
    }

    private void setupClosing(JFrame j) {
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String getTitle(JPanel panel) {
        String x = panel.getClass().getName();
        String y = x.substring(x.lastIndexOf(".") + 1);
        return y;
    }

    /**
     * Sets the icon of the window
     * @param m The <code>Image</code> object holding the icon picture
     */
    public void setIconImage(Image m) {
        frame.setIconImage(m);
    }

    /**
     * Sets if the window is resizable or not
     * @param b true if resizable, false otherwise
     */
    public void setResizable(boolean b) {
        frame.setResizable(b);
    }

    /**
     * Resizes the window/ panel
     * @param width The new width of the panel on screen
     * @param height The new height of the panel on screen
     */
    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    /**
     * Runs the panel
     */
    public void run() {
        frame = new JFrame(getTitle(panel));
        setupClosing(frame);
        frame.getContentPane().add(panel);
        frame.setBounds(400, 300, w, h);
        frame.setVisible(true);
    }

    public static void main(String[] rags) {
        Console c = new Console(new JPanel(), 500, 400);
        c.run();
    }
}
