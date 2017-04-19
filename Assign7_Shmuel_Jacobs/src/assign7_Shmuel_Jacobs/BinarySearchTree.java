/**
 * CIS2168 Spring 2017 Data Structures
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 5
 * BinarySearchTree
 * The parent class of BinarySearchTree is the BinaryTree1 class
 *   that was covered in Lec#14, Lec#15.
 * The interface SearchTree implemented by BinarySearchTree was
 *   also covered in Lec#14.
 * Both files SearchTree.java and BinaryTree1.java are included in the folder here.
 *
 * @param <E> Datatype stored in list
 */
package bst4stu;

/*<listing chapter="6" section="4">*/
//package KW.CH06;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to represent a binary search tree.
 *
 * @author Koffman and Wolfgang modified by Shmuel Jacobs
 */
public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree1<E>
        implements SearchTree<E> {
    // Data Fields

    /**
     * Return value from the public add method.
     */
    protected boolean addReturn;
    /**
     * Return value from the public delete method.
     */
    protected E deleteReturn;

    //Methods
    /*<listing chapter="6" number="3">*/
    /**
     * Starter method find.
     *
     * @pre The target object must implement the Comparable interface.
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     */
    @Override
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     *
     * @param localRoot The local subtree�s root
     * @param target The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /*</listing>*/

 /*<listing chapter="6" number="4">*/
    /**
     * Starter method add.
     *
     * @pre The object to insert must implement the Comparable interface.
     * @param item The object being inserted
     * @return true if the object is inserted, false if the object already
     * exists in the tree
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * Recursive add method.
     *
     * @post The data field addReturn is set true if the item is added to the
     * tree, false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            addReturn = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /*</listing>*/

 /*<listing chapter="6" number="5">*/
    /**
     * Starter method delete.
     *
     * @post The object is not in the tree.
     * @param target The object to be deleted
     * @return The object deleted from the tree or null if the object was not in
     * the tree
     * @throws ClassCastException if target does not implement Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive method to remove data item from tree rooted at given node
     * and fill its spot in the tree with in-order successor
     * 
     * @param localRoot root of tree to remove from
     * @param target data item to remove from tree
     * @return node holding target value, if found, else null
     */
    private Node delete2(Node<E> localRoot, E target){
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }
        int compResult = target.compareTo(localRoot.data);
        if(compResult < 0){//target is less than root--search left
            localRoot.left = delete2(localRoot.left, target);
            return localRoot;
        }
        else if(compResult > 0 ){
            localRoot.right = delete2(localRoot.right, target);
            return localRoot;
        }//only possibility left is that localRoot has target value
        else{
            deleteReturn = localRoot.data;
            //replace root with leftmost node in right subtree
            if(localRoot.right == null){/* root has greatest value in tree,
                so just remove by returning left subtree */
                return localRoot.left;
            }
            else if(localRoot.left == null){
                return localRoot.right;
            }
            else{
                /* Last step before trying recursive solution:
                check if root.right.left exists. If not, root.right is
                leftmost node in right subtree */
                if(localRoot.right.left == null){
                    localRoot.data = localRoot.right.data;
                    localRoot.right = localRoot.right.right;//cut root.right out of sub
                    return localRoot;
                }
                else{
                    localRoot.data = findSmallestChild(localRoot.right);
                    return localRoot;
                }
            }
        }
    }
    
    /**
     * Wrapper method to remove data item from calling tree and replace it with
     * in-order successor
     * @param target value to remove
     * @return data value removed, null if target value wansn't in tree
     */
    public E delete2(E target) {
        root = delete2(root, target);
        return deleteReturn;
    }
    
    /**
     * Recursive delete method.
     *
     * @post The item is not in the tree; deleteReturn is equal to the deleted
     * item as it was stored in the tree or null if the item was not found.
     * @param localRoot The root of the current subtree
     * @param item The item to be deleted
     * @return The modified local root that does not contain the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /*</listing>*/

 /*<listing chapter="6" number="6">*/
    /**
     * Find the node that is the inorder predecessor and replace it with its
     * left child (if any).
     *
     * @post The inorder predecessor is removed from the tree.
     * @param parent The parent of possible inorder predecessor (ip)
     * @return The data in the ip
     */
    private E findLargestChild(Node<E> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }
    
    /**
     * Find the node that is the inorder successor and replace it with its
     * left child (if any).
     *
     * @post The inorder successor is removed from the tree.
     * @param parent The parent of possible inorder successor (ip)
     * @return The data in the ip
     */
    private E findSmallestChild(Node<E> parent) {
        // If the left child has no left child, it is
        // the inorder successor.
        if (parent.left.left == null) {
            E returnValue = parent.left.data;
            parent.left = parent.left.right;
            return returnValue;
        } else {
            return findSmallestChild(parent.left);
        }
    }

    /*</listing>*/
    /**
     * @Deprecated
     */
    public boolean contains(E target) {
        return false;
    }

    /**
     * @Deprecated
     */
    public boolean remove(E target) {
        return false;
    }

    /**
     * Public wrapper for creating pre-order traversal String for calling tree.
     *
     * @return String representation of pre-order of tree
     */
    public String preOrderTraversal() {
        return preOrderTraversal(root);
    }

    /**
     * Recursive Pre-order traversal String method
     *
     * @param localRoot root of the tree to traverse
     * @return String representation of pre-order traversal
     */
    private String preOrderTraversal(Node<E> localRoot) {
        String returnString = "";
        if (localRoot == null) {
            return returnString;
        }
        returnString += String.valueOf(localRoot.data) + "  ";
        if (localRoot.left != null) {
            returnString += preOrderTraversal(localRoot.left);
        }
        if (localRoot.right != null) {
            returnString += preOrderTraversal(localRoot.right);
        }
        return returnString;
    }

    /**
     * Public wrapper for creating post-order traversal String for calling tree.
     *
     * @return String representation of post-order traversal
     */
    public String postOrderTraversal() {
        return postOrderTraversal(root);
    }

    /**
     * Recursive Post-order traversal String method
     *
     * @param root root of the tree to traverse
     * @return String representation of post-order traversal
     */
    private String postOrderTraversal(Node<E> localRoot) {
        String returnString = "";//empty tree returns empty String
        if (localRoot == null) {//tree is empty
            return returnString;
        }
        if (localRoot.left != null) {//if left subtree exists, traverse it
            returnString += postOrderTraversal(localRoot.left);
        }
        if (localRoot.right != null) {//if right subtree exists, traverse it
            returnString += postOrderTraversal(localRoot.right);
        }
        returnString += String.valueOf(localRoot.data) + "  ";//append root
        return returnString;
    }

    /**
     * Public wrapper for creating in-order traversal String for calling tree
     *
     * @return String representation of in-order traversal
     */
    public String inOrderTraversal() {
        return inOrderTraversal(root);
    }

    /**
     * Recursive In-order traversal String method
     *
     * @param root root of the tree to traverse
     * @return String representation of in-order traversal
     */
    private String inOrderTraversal(Node<E> localRoot) {
        String returnString = "";//empty tree returns empty String
        if (localRoot == null) {//tree is empty
            return returnString;
        }
        if (localRoot.left != null) {//if left subtree exists, traverse it
            returnString += inOrderTraversal(localRoot.left);
        }
        //append root after left subtree traversal
        returnString += String.valueOf(localRoot.data) + "  ";
        if (localRoot.right != null) {//if right subtree exists, traverse it
            returnString += inOrderTraversal(localRoot.right);
        }
        return returnString;
    }

    /**
     * Recursive method for deep copying tree rooted at copyRoot into tree
     * rooted at localRoot. pre: both nodes are initialized For reliable
     * behavior, call passing localRoot a new node holding null in all
     * variables.
     *
     * @thows NullPointerException if either root passed isn't initialized
     * @param copyRoot root of tree to copy
     * @param localRoot node to root newly built copy at
     */
    private void copy(Node<E> copyRoot, Node<E> localRoot) {
        localRoot.data = copyRoot.data;
        //local root is now (<<data>>  null  null)
        if (copyRoot.left != null) {
            localRoot.left = new Node(null);
            copy(copyRoot.left, localRoot.left);
        }
        if (copyRoot.right != null) {
            localRoot.right = new Node(null);
            copy(copyRoot.right, localRoot.right);
        }
    }

    /**
     * Public wrapper for copying from tree in arguments to calling tree.
     *
     * @param copyFrom tree to copy into calling tree
     * @post If argument tree isn't null, calling tree is a copy of it.
     */
    public void copy(BinarySearchTree<E> copyFrom) {

        if (copyFrom != null) {//check that tree exists
            if(copyFrom.root == null){//case: tree exists and is empty
                root = null;
            }
            //case: tree exists and has rot
            root = new Node<E>(null);
            copy(copyFrom.root, root);
        }
    }

    /**
     * Recursive method for checking equality of tree rooted at compareRoot
     * and tree rooted at localRoot
     * @param compareRoot root of one tree
     * @param localRoot root of other tree, to be passed root of calling tree
     * @return true if trees match, false otherwise
     */
    private boolean equals(Node<E> compareRoot, Node<E> localRoot){
        //deal with both empty tree cases
        if(compareRoot == null || localRoot == null){
            return localRoot == compareRoot;//operator works here, because it's checking null
        }
        /* could easily become "return a.data.equals(...) && equals(x,y) &&
        equals(u,v) but this is more readible */
        boolean matches = true;
        if(!compareRoot.data.equals(localRoot.data)){
            matches = false;
        }
        //once matches is false, no need to evaluate next cases
        else if(!equals(localRoot.left, compareRoot.left)){
        //our tree has a left subtree to compare and it's not equal
            matches = false;
        }
        else if(!equals(localRoot.right, compareRoot.right)){
            matches = false;
        }
        return matches;
    }
    
    /**
     * Public wrapper for checking equality of trees
     * 
     * @param compareTree tree to compare to calling tree
     * @return true if trees match, false otherwise
     */
    public boolean equals(BinarySearchTree compareTree) {
        //calling tree is never equal to null
        if(compareTree == null){
            return false;
        }
        return equals(compareTree.root, root);
    }
}
/*</listing>*/
