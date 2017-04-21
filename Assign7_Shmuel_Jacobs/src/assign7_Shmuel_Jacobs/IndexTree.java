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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexTree {

    protected IndexEntryNode root;

    //TODO: This is dummy method. Find a place to add it really, and decide bool
    public boolean addListing(String word, Collection<Integer> places) {
        IndexEntryNode insertion = new IndexEntryNode(word.toLowerCase(), places);
        if (root == null) {
            root = insertion;
            return true;
        } else {
            return addListing(root, insertion);
        }
    }

    /**
     * Recursive method for adding new listings. Do not pass in a null root.
     * Check for that in public.
     *
     * @param localRoot
     * @param insertion
     * @return true if listing was added
     */
    private boolean addListing(IndexEntryNode localRoot,
            IndexEntryNode insertion) {
        boolean added = false;
        //case: insertion is less than localRoot
        if (localRoot.compareTo(insertion) > 0) {
            if (localRoot.left == null) {
                localRoot.left = insertion;
                added = true;
            } else {
                added = addListing(localRoot.left, insertion);
            }
        } //case: insertion is greater than localRoot
        else if (localRoot.compareTo(insertion) < 0) {
            if (localRoot.right == null) {
                localRoot.right = insertion;
                added = true;
            } else {
                added = addListing(localRoot.right, insertion);
            }
        }
        //flow shouldn't reach here except when listings match
        return added;
    }

    /**
     * Print out entire index listing. Calls in0order traversal helper method.
     *
     * @return String of entire index.
     */
    @Override
    public String toString() {
        StringBuilder inOrderSB = new StringBuilder();
        inOrderTraversalHelper(root, new NodePrinter(), inOrderSB);

        return inOrderSB.toString();
    }

    /*
    private String toString(IndexEntryNode localRoot){
        
        if(localRoot)
    } */
    /**
     * Private helper method for methods that require inOrderTraversal.
     *
     * @param localRoot root of the tree to traverse
     * @param func functional object implementing method workOnNode, the method
     * to call at each node
     * @param output StringBuilder or other object capable of carrying output
     */
    private void inOrderTraversalHelper(IndexEntryNode localRoot,
            NodeWorker func, StringBuilder output) {
        if (localRoot != null) {
            inOrderTraversalHelper(localRoot.left, func, output);
            func.workOnNode(localRoot, output);
            inOrderTraversalHelper(localRoot.right, func, output);

        }
    }
}

/**
 * Implementation of NodeWorker interface, to be used to print the node it's
 * given.
 *
 * @author Shmuel Jacobs
 */
class NodePrinter implements NodeWorker {

    @Override
    public void workOnNode(IndexEntryNode node, StringBuilder output) {
        output.append(node.getWord());
        for (int i = 0;
                i < node.numberListings();
                i++) {
            output.append(i + "  ");
        }
        output.append('\n');
    }
}
