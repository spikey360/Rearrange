/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rearrangeword.rearrangegui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import rearrangeword.rearrange.Main;
import rearrangeword.swing.Console;

/**
 *
 * @author Riju
 */
public class Rearrange extends JApplet {

    private JTextField inWord;
    private JTextArea outWords;
    private JButton rearrangeButton;
    private JLabel label;
    private JProgressBar pb;
    private Main rMain;

    class RearrangeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Runnable runner = new Runnable() {

                public void run() {
                    rMain = new Main();

                    outWords.setText("");
                    if (inWord.getText().length() > 9) {
                        outWords.setText("Too long to rearrange! Less than 9 letters required!");
                        return;
                    }

                    rMain.rearrange(inWord.getText());

                    //pu.completed=true;
                    Object[] solvs = rMain.getWords().toArray();
                    for (int i = 0; i < solvs.length; i++) {
                        outWords.append((String) solvs[i] + "\n");
                    }
                    if (rMain.getWords().size() == 0) {
                        outWords.append("Solution not found!");
                    }
                }
            };
            Thread xRun = new Thread(runner);

            xRun.start();



        }
    }

    class ProgressUpdater extends Thread {

        Main x;
        boolean complete = false;

        ProgressUpdater() {
            //d = x;
        }

        public void run() {
            pb.setMinimum(0);
            pb.setMaximum(rMain.getMax() - rMain.getMin());
            if (complete == false) {
                pb.setValue(rMain.getCurrent());
            }
        }
    }

    Rearrange() {
        //look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //initialize
        //rMain = new Main();
        inWord = new JTextField();
        outWords = new JTextArea(10, 2);
        rearrangeButton = new JButton("Rearrange!");
        rearrangeButton.addActionListener(new RearrangeListener());
        pb = new JProgressBar();
        label = new JLabel("Created by spikey360-spikey360@yahoo.co.in");
    //add actionListeners
    }

    public void init() {
        Container cp = getContentPane();
        Box b1 = Box.createHorizontalBox();
        b1.add(inWord);
        b1.add(rearrangeButton);
        cp.add(b1, BorderLayout.NORTH);
        Box b2 = Box.createHorizontalBox();
        b2.add(label);
        //b2.add(pb);
        cp.add(b2, BorderLayout.SOUTH);
        cp.add(outWords, BorderLayout.CENTER);
    }

    public void start() {
    }
    static java.net.URL imgUrl = Rearrange.class.getResource("rearrangeGUIicon.png");
    static Image ico = new ImageIcon(imgUrl).getImage();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Console c = new Console(new Rearrange(), 300, 200);
        c.run();
        c.setIconImage(ico);
    }
}
