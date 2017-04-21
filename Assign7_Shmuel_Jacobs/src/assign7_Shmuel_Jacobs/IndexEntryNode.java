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
    
    public int compareTo(IndexEntryNode listing){
        return word.compareTo(listing.getWord());
    }
    
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
}
