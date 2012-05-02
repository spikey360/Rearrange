/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rearrangeword.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Riju
 */
public class Lexicon {

    private InputStream is;
    private int words;
    String comment; //for verbose comments!
    private LexiconNode wordTree;
    final int MINLENGTH = 2;
    final int MAXLENGTH = 9;

    public Lexicon() {
        is = Lexicon.class.getResourceAsStream("lexicon.txt");
        //create tree
        createLexiconTree();

        //populate tree
        populateTree();
    }

    private void createLexiconTree() {
        //for now, we'll create two levels of trees
        //later, we've to find an algoritm which determines the optimum level of trees

        wordTree = new LexiconNode(-1); //be careful as not to addWord here as that'll cause out of bounds exception
        //now we instruct to create substructure
        wordTree.createSubstructure();//each is an alphabet node
        //and their substructure
        for (char i = LexiconNode.BEGINLETTER; i <= LexiconNode.ENDLETTER; i++) {
            LexiconNode ln = wordTree.getNode(i);
            ln.createSubstructure();//done! each is a subalphabetnode(level 1)
        }



    }

    private void populateTree() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String x = "";
            while ((x = in.readLine()) != null) {
                char bChar = x.charAt(0);
                //Character z=new Character(bChar);
                LexiconNode siblingNode = wordTree.getNode(bChar);
                if (x.length() >= MINLENGTH && x.length()<MAXLENGTH) {//code optimized to not accomodate words that'll never be searched for
                    siblingNode.addWord(x);
//                System.err.println(siblingNode.getLevel());
                    words++;
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(Lexicon.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error reading file lexicon.txt");
        }
    }

    public void printTree() {
        //2 levels only
        for (char i = LexiconNode.BEGINLETTER; i <= LexiconNode.ENDLETTER; i++) {
            System.out.println("Node: " + i);
            LexiconNode ln = wordTree.getNode(i);
            for (char j = LexiconNode.BEGINLETTER; j <= LexiconNode.ENDLETTER; j++) {
                LexiconNode nln = ln.getNode(j);
                System.out.println("\tNode: " + j);
                for (int k = 0; k < nln.getWords().size(); k++) {
                    System.out.println("\t\t" + nln.getWords().get(k));
                }
            }
        }
        System.out.println("" + words + " words");
    }

    public boolean hasWord(String x) {
        char a = x.charAt(0);
        char b = x.charAt(1);
        LexiconNode ln = wordTree.getNode(a);
        LexiconNode nln = ln.getNode(b);
        if (nln.getWords().size() > 0) {
            return nln.getWords().contains(x);
        //return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Lexicon ln = new Lexicon();
        ln.printTree();//works too!
        System.out.println("Has word: aardvark?:" + ln.hasWord("aardvark"));
        System.out.println("Has word: ardvark?:" + ln.hasWord("ardvark"));
        System.out.println("Has word: sensuous?:" + ln.hasWord("sensuous"));
    }
}
