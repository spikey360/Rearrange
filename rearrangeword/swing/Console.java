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
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Riju M
 */
public class Console {
    
    
    private JFrame frame;
    private int w,h;
    private JApplet applet;
    
    /**
     * Creates a new instance of this class which diaplays a JApplet on scree
     * @param appletInstance The JApplet required to be displayed
     * @param width The width of the applet on screen
     * @param height The height of the applet
     */
    public Console(JApplet appletInstance,int width,int height){
        applet=appletInstance;
        w=width;
        h=height;
    }
    /**
     * Creates a new instance of this class which displays a JPanel on screen
     * @param panelInstance The panel required to be displayed
     * @param width The width of the panel
     * @param height The height of the panel
     */
    public Console(JPanel panelInstance,int width,int height){
        applet=new JApplet();
        applet.getContentPane().add(panelInstance);
        w=width;
        h=height;
    }
    private void setupClosing(JFrame j){
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private String getTitle(JApplet frame){
        String x=frame.getClass().getName();
        String y=x.substring(x.lastIndexOf(".")+1);
        return y;
    }
    /**
     * Sets the icon of the window
     * @param m The <code>Image</code> object holding the icon picture
     */
    public void setIconImage(Image m){
        frame.setIconImage(m);
    }
    /**
     * Sets if the window is resizable or not
     * @param b true if resizable, false otherwise
     */
    public void setResizable(boolean b){
        frame.setResizable(b);
    }
    /**
     * Resizes the window/ applet
     * @param width The new width of the applet on screen
     * @param height The new height of the applet on screen
     */
    public void setSize(int width,int height){
        frame.setSize(width,height);
    }
    /**
     * runs the applet
     */
    public void run(){
        frame=new JFrame(getTitle(applet));
        setupClosing(frame);
        frame.getContentPane().add(applet);
        applet.init();
        applet.start();
        frame.setBounds(400,300,w,h);
        //frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] rags){
	Console c=new Console(new JApplet(),500,400);
	c.run();
    }
}
