/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Index Tree
 * Binary Search tree whose nodes each contain information of 
 * an index listing. Tree implements an index.
 */
package assign7_Shmuel_Jacobs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexTree {
    
    protected IndexEntryNode root;
    
    //TODO: This is dummy method. Find a place to add it really, and decide bool
    public boolean addListing(String word,
            int numOccurrences, Collection<Integer> places){
        root = new IndexEntryNode(word.toLowerCase(), places);
        return true;
    }
    
    //TODO: Dummy method. Recursively print all elements. Create recursive and wrap
    @Override
    public String toString(){
        return root.toString();
    }
}
