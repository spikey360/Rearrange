/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rearrangeword.rearrangegui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class Rearrange extends JPanel {

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
        }

        public void run() {
            pb.setMinimum(0);
            pb.setMaximum(rMain.getMax() - rMain.getMin());
            if (!complete) {
                pb.setValue(rMain.getCurrent());
            }
        }
    }

    public Rearrange() {
        //look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //initialize
        inWord = new JTextField();
        outWords = new JTextArea(10, 2);
        rearrangeButton = new JButton("Rearrange!");
        rearrangeButton.addActionListener(new RearrangeListener());
        pb = new JProgressBar();
        label = new JLabel("Created by spikey360-spikey360@yahoo.co.in");
    }

    public void initialize() {
        setLayout(new BorderLayout());
        Box b1 = Box.createHorizontalBox();
        b1.add(inWord);
        b1.add(rearrangeButton);
        add(b1, BorderLayout.NORTH);
        Box b2 = Box.createHorizontalBox();
        b2.add(label);
        add(b2, BorderLayout.SOUTH);
        add(outWords, BorderLayout.CENTER);
    }

    static java.net.URL imgUrl = Rearrange.class.getResource("rearrangeGUIicon.png");
    static Image ico = new ImageIcon(imgUrl).getImage();

    /**
     * @param args the command line arguments
     */
    /**
     * The main method serves as the entry point for the application.
     * It initializes a Console object with a Rearrange instance and specified dimensions,
     * starts the console, and sets an icon image for the console window.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Rearrange panel = new Rearrange();
        panel.initialize();
        Console c = new Console(panel, 300, 200);
        c.run();
        c.setIconImage(ico);
    }
}
