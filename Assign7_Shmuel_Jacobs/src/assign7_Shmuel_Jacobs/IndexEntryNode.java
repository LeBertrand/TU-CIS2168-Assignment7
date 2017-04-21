/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Index Entry 
 * Data Object holding information for index entry,
 * and functioning as node in Binary Search Tree that implements Index
 */
package assign7_Shmuel_Jacobs;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexEntryNode{
    private String word;
    private int numOccurrences;
    private ArrayList<Integer> places;
    
    IndexEntryNode left;
    IndexEntryNode right;
    
    //TODO: Don't manually enter numOccurrences. Automatically take length of list
    //TODO: Would I rather declare places a List<Integer> ?
    IndexEntryNode(String word, Collection<Integer> places){
        this.word = word;
        numOccurrences = places.size();
        this.places = new ArrayList(places);
        //use abstract super to allow arrays to be passed into same constructor
    }
    
    protected String getWord(){
        //bad data hiding--clone then pass?
        return word;
    }
    
    public int numberListings(){
        return places.size();
    }
    
    /**
     * Compare index listings to find precedence.
     * Really just compares Strings in the "word" field.
     * @param listing index entry to compare to
     * @return positive int if calling entry is greater value than parameter,
     * 0 if they're equal, negative int otherwise.
     */
    public int compareTo(IndexEntryNode listing){
        return word.compareTo(listing.getWord());
    }
    
    /**
     * Create String to show index entry, with lookup word
     * and list of appearances.
     * @return String representation of index entry
     */
    @Override
    public String toString(){
        String display = "";
        display += word;
        display += ": ";
        for(Integer place: places){
            display += place.toString() + "  ";
        }
        return display;
    }
    
    /**
     * Check if this entry is the same as the entry in parameter.
     * Used by tree to detect and handle duplicates.
     * @param node another entry to compare
     * @return true is entries list same word
     */
    public boolean equals(IndexEntryNode node){
        return word.equals(node.getWord());
    }
    
    /**
     * Check if node is a leaf
     * @return true if both subtrees are empty, false otherwise
     */
    public boolean isLeaf(){
        return (right == null && left == null);
    }
}
