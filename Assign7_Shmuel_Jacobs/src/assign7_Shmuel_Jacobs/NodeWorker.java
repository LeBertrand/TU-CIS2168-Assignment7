/*
 * Functional interface goes in pre-order traversal helper method.
 */
package assign7_Shmuel_Jacobs;

/**
 *
 * @author Shmuel Jacobs
 */
public interface NodeWorker {
    public abstract void workOnNode(IndexEntryNode node, StringBuilder output);
}
