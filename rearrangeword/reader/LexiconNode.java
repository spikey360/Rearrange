/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rearrangeword.reader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * It's a wrapper for a HashMap
 * @author Riju
 */
public class LexiconNode {
    private HashMap subMap;
    private ArrayList words;
    private boolean root;
    private int level; //sets the current level initial is 0 ; -1, lower for root
    public static final char BEGINLETTER='a';
    public static final char ENDLETTER='z';

    public LexiconNode(int level){
        subMap=new HashMap();
        words=new ArrayList();
        this.level=level;
        if(level<=-1)
            root=true;
    }

    public boolean isRoot(){
        return root;
    }

    public void createSubstructure(){
        //current lexiconnode is our only concern nothing beyond it
        for(char i=BEGINLETTER;i<=ENDLETTER;i++){
            subMap.put(new Character(i), new LexiconNode(level+1));
            //cause the next lexiconnode is a subordinate
        }
    }

    public LexiconNode getNode(char x){
        return (LexiconNode)subMap.get(new Character(x));
    }

    public void addWord(String xl){
        String x=xl.toLowerCase();
        Character begin=new Character(x.charAt(level+1));
        LexiconNode ln=getNode(begin);
        ln.words.add(x);
    }

    public ArrayList getWords(){
        return words;
    }

    public int getLevel(){
        return level;
    }

    public static void main(String args[]){
        LexiconNode ln=new LexiconNode(0);
        ln.createSubstructure();
        ln.addWord("arash");
        ln.addWord("Guitara");
        LexiconNode nln=ln.getNode('u');
        System.out.println(nln.getWords().get(0));
        //test results are a success!
    }

}
