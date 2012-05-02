/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rearrangeword.rearrange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import rearrangeword.reader.Lexicon;

/**
 *
 * @author Riju
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    String[] letters;
    int max;
    int min;
    int c;
    String buffer;
    Lexicon lex;
    HashSet arrangedWords;

    public HashSet getWords(){
        return arrangedWords;
    }

    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
    }
    public int getCurrent(){
        return c;
    }

    private void assignNumber(String s) {
        //first we assign each letter to each number 1 through 9;
        letters = new String[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            letters[i + 1] = "" + s.charAt(i);
        }

        //!
        System.out.print("Rearranging: ");
        for (int k = 1; k < letters.length; k++) {
            System.out.print(letters[k]);
        }
        System.out.println();
    //!

    }

    private void setRange(String s) {
        //setting min
        for (int i = 0; i < s.length(); i++) {
            min = (min * 10) + (i + 1);
        }
        //System.err.println("Min:" + min);
        for (int i = s.length(); i > 0; i--) {
            max = (max * 10) + i;
        }
        max++; //code optimized to not go further than the practical max limit
        //System.err.println("Max:" + max);
    }

    private boolean digitsRecurr(int x) {
        String dig = Integer.toString(x);
        int z = 0;
        for (int i = 0; i < dig.length() - 1; i++) {
            String u = Character.toString(dig.charAt(i));
            //now we break the string into two parts such that
            //we can search each part for recurrence. Otherwise indexOf
            //will return the same value and a bug will be created
            String up = dig.substring(0, i);
//            z=i;
//            if(i==dig.length())
//                z--;
            String down = dig.substring(i + 1); //includes sample
            //treat up first
            z = up.indexOf(u);
            if (z >= 0)//recurrs
            {
                return true;
            }
            //treat down
            z = down.indexOf(u);
            if (z >= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean allOccur(int x) {
        String z = Integer.toString(x);
        for (int i = 0; i < letters.length - 1; i++) {
            String d = "" + (i + 1);
            if (!(z.indexOf(d) >= 0)) {
                return false;
            }
        }
        return true;
    }

    private String getRearrangedString(int x) {
        String s = "";
        String num = Integer.toString(x);
        for (int i = 0; i < num.length(); i++) {
            int p = Integer.parseInt(Character.toString(num.charAt(i)));
            s += letters[p];
        }
        return s;
    }

    public void rearrange(String s) {
        arrangedWords=new HashSet(); c=0;
        //load the lexicon
        lex=new Lexicon();
        //Now we get to the nitty gritty of rearranging
        //first assign numbers
        assignNumber(s);
        //then decide which are the minimum and the maximum numbers
        setRange(s);
        //now increament one number and check for digit recurrunce
        String rString="";
        for (int i = min; i < max; i++) {
            c++;
            if (allOccur(i)) {
//                System.out.println(getRearrangedString(i));
                rString=getRearrangedString(i);
                if(lex.hasWord(rString)){
                    arrangedWords.add(rString);
                    System.out.println(rString);
                }

            }
        }


    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.print("What to rearrange? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        if (s.length() > 9) {
            System.err.println("To long to rearrange!");
            return;
        }
        new Main().rearrange(s);
        
    }
}
