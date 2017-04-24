/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Index Entry 
 * Data Object holding information for index entry,
 * and functioning as node in Binary Search Tree that implements Index
 * Redundancies are built in to ignore case, but bugs may exist
 */
package assign7_Shmuel_Jacobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexEntryNode{
    private final String word;
    private int numOccurrences;
    private ArrayList<Integer> places;
    
    IndexEntryNode left;
    IndexEntryNode right;
    
    /**
     * Create new node index entry.
     * @param word String holding word to index
     * @param places list to hold line numbers for all occurrences of word
     */
    public IndexEntryNode(String word, Collection<Integer> places){
        this.word = word.toLowerCase();
        numOccurrences = places.size();
        //Any use in sorting listing? Requires new declaration and reader goes in order anyway
        this.places = new ArrayList(places);
        //use abstract super to allow passed into same constructor
    }
    
    /**
     * Get string holding word indexed.
     */
    protected String getWord(){
        //bad data hiding--except word is final
        return word;
    }
    
    /**
     * Return the number of times this word appears. 
     */
    public int numberListings(){
        return places.size();
    }
    
    /**
     * Return the actual list of places where word was found.
     */
    public ArrayList listPlaces(){
        return (ArrayList) places.clone();
    }
    
    /**
     * Overwrite the current list of places where this word was found.
     * @param places new list of places
     */
    protected void setPlaces(List<Integer> places){
        this.places = new ArrayList(places);
        numOccurrences = places.size();
    }
    
    /**
     * Add another to the list of places where this word was found.
     * @param place line number where word was found
     */
    protected void appendPlaces(int place){
        places.add(place);
    }
    
    
    
    /**
     * Compare index listings to find precedence.
     * Really just compares Strings in the "word" field.
     * @param listing index entry to compare to
     * @return positive int if calling entry is greater value than parameter,
     * 0 if they're equal, negative int otherwise.
     */
    public int compareTo(IndexEntryNode listing){
        return word.compareToIgnoreCase(listing.getWord());
    }
    
    /**
     * Compare word to a String in parameter
     * @param word string to compare to local word
     * @return positive int if calling entry is greater value than parameter,
     * 0 if they're equal, negative int otherwise.
     */
    public int compareTo(String word){
        return this.word.compareToIgnoreCase(word);
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
        return word.equalsIgnoreCase(node.getWord());
    }
    
    /**
     * Check if node is a leaf
     * @return true if both subtrees are empty, false otherwise
     */
    public boolean isLeaf(){
        return (right == null && left == null);
    }
}
