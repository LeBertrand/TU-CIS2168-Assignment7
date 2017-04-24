/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Index Tree
 * Binary Search tree whose nodes each contain information of 
 * an index listing. Tree implements an index.
 * Contains helper method for in-order traversal, but not for a binary depth
 * travel, because find, add and remove behave so differently.
 */
package assign7_Shmuel_Jacobs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexTree {

    protected IndexEntryNode root;

    /**
     * Insert new word into index, wrapping into a node and putting node in
     * tree.
     *
     * @param word word to add to index
     * @param places line numbers where word has been found
     * @return true if node is inserted, false otherwise
     */
    public boolean addListing(String word, List<Integer> places) {
        IndexEntryNode insertion = new IndexEntryNode(word.toLowerCase(), places);
        return addListing(insertion);
    }

    /**
     * Insert new word into index, wrapping into a node and putting node in
     * tree.
     *
     * @param word word to add to index
     * @return true if node is inserted, false otherwise
     */
    public boolean addListing(String word) {
        IndexEntryNode insertion = new IndexEntryNode(
                word.toLowerCase(), new ArrayList<>());
        return addListing(insertion);
    }

    /**
     * Insert new node into index tree
     *
     * @param insertion listing to insert into tree
     * @return
     */
    public boolean addListing(IndexEntryNode insertion) {
        if (root == null) {
            root = insertion;
            return true;
        } else if (root.equals(insertion)) {
            //case: update occurring at root
            insertion.left = root.left;
            insertion.right = root.right;
            root = insertion;
            return false;
        } else {
            return addListing(root, insertion);
        }
    }

    /**
     * Recursive method for adding new listings. Do not pass in a null root.
     * Check for that in public.
     *
     * @param localRoot root of tree to add listing to
     * @param insertion node containing listing to add
     * @return true if listing was added
     */
    private boolean addListing(IndexEntryNode localRoot,
            IndexEntryNode insertion) {
        boolean added = false;
        //case: update existing node
        if (localRoot.equals(insertion)) {
            localRoot.setPlaces(insertion.listPlaces());
        }//inneficient--just here for safety--updates should use append method
        //case: insertion is less than localRoot
        else if (localRoot.compareTo(insertion) > 0) {
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
        return added;
    }

    /**
     * Recursive method for finding listing in tree
     *
     * @param localRoot root of tree to search
     * @param word value to look for among listings in tree
     * @return null if value not found, or node containing target word
     */
    private IndexEntryNode find(IndexEntryNode localRoot, String word) {
        //case: reached null node, which means it's not here
        if (localRoot == null) {
            return localRoot;
        } else if (localRoot.getWord().equals(word)) {
            return localRoot;
        } else if (localRoot.compareTo(word) > 0) {
            //local root is greater than target word
            return find(localRoot.left, word);
        }//last case: local roo is less than target word
        return find(localRoot.right, word);
    }

    /**
     * Public wrapper for finding listing in tree
     *
     * @param word target String to search for
     * @return null if value not found, otherwise node containing target word
     */
    public IndexEntryNode find(String word) {
        return find(root, word);
    }

    /**
     * Print out entire index listing. Calls in0order traversal helper method.
     *
     * @return String of entire index.
     */
    @Override
    public String toString() {
        StringBuilder inOrderSB = new StringBuilder();
        inOrderTraversalHelper(root, (IndexEntryNode node, StringBuilder output) -> {
            output.append(node.getWord()).append(": ");
            List places = node.listPlaces();
            for (int i = 0;
                    i < node.numberListings();
                    i++) {
                output.append(places.get(i)).append("  ");
            }
            output.append('\n');
        }, inOrderSB);

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
