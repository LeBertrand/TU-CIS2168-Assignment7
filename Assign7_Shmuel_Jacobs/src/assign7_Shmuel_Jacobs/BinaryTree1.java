package assign7_Shmuel_Jacobs;

/*<listing chapter="6" section="3">*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Class for a binary tree that stores type E objects.
 *
 * @author Koffman and Wolfgang with additions by Shmuel Jacobs
 *
 */
public class BinaryTree1<E> implements Serializable {

    /*<listing chapter="6" number="1">*/
    /**
     * Class to encapsulate a tree node.
     */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /**
         * The information stored in this node.
         */
        public E data;
        /**
         * Reference to the left child.
         */
        public Node<E> left;
        /**
         * Reference to the right child.
         */
        public Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
    /*</listing>*/
    // Data Field
    /**
     * The root of the binary tree
     */
    protected Node<E> root;

    /**
     * Construct an empty BinaryTree1
     */
    public BinaryTree1() {
        root = null;
    }

    /**
     * Construct a BinaryTree1 with a specified root. Should only be used by
     * subclasses.
     *
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree1(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree as its left
     * subtree and rightTree as its right subtree.
     */
    public BinaryTree1(E data, BinaryTree1<E> leftTree,
            BinaryTree1<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root or the left subtree
     * is null
     */
    public BinaryTree1<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree1<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     *
     * @return the right sub-tree or null if either the root or the right
     * subtree is null.
     */
    public BinaryTree1<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree1<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     *
     * @return the data field of the root or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     *
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
            StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /*<listing chapter="6" number="2">*/
    /**
     * Method to read a binary tree.
     *
     * @pre The input consists of a preorder traversal of the binary tree. The
     * line "null" indicates a null tree.
     * @param scan the Scanner attached to the input file
     * @return The binary tree
     */
    public static BinaryTree1<String> readBinaryTree1(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree1<String> leftTree = readBinaryTree1(scan);
            BinaryTree1<String> rightTree = readBinaryTree1(scan);
            return new BinaryTree1<String>(data, leftTree, rightTree);
        }
    }

    /*</listing>*/
 /*</listing>*/
    /**
     * Returns depth of highest occurrence of given node. Note that in non-BSTs
     * node can appear twice, so both subtrees are considered.
     *
     * @param node node to search tree for
     */
    private int depth(Node node) {
        if (root == node) {
            return 1;
        }
        int heightL = new BinaryTree1(root.left).depth();//get height of left
        int heightR = new BinaryTree1(root.right).depth();//get height of right
        if (heightL >= heightR) {//height of left is at least equal
            return 1 + heightL;//height of tree is one more than height of left
        }
        return 1 + heightR;

    }

    /**
     * Returns height of tree rooted at given node.
     *
     * @param rootedAt root of subtree to evaluate
     */
    private int height(Node rootedAt) {
        if (rootedAt == null) {
            return 0;
        }
        int heightL = height(rootedAt.left);//get height of left
        int heightR = height(rootedAt.right);//get height of right
        if (heightL >= heightR) {//height of left is at least equal
            return 1 + heightL;//height of tree is one more than height of left
        }
        return 1 + heightR;//height of tree is one more than height of right
    }

    /**
     * Returns (greatest depth in, or the) height of calling tree
     */
    public int depth() {//really returns height of tree
        return height(root);
    }

    /**
     * Return the number of nodes in tree
     */
    public int size() {
        int count = 1;
        if (root.left != null) {
            count += getLeftSubtree().size();
        }
        if (root.right != null) {
            count += getRightSubtree().size();
        }
        return count;
    }
}
