/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Main Application Class
 * Read data in file named in first arg.
 * Create a tree and place that data into it.
 * Allow user to perform searches.
 * Write the in-order traversal of that tree to the file named in second arg.
 */
package assign7_Shmuel_Jacobs;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexTreeTest {

    /**
     * @param args names of file to read from and file to write to
     */
    public static void main(String[] args) {
        String fileIn = args[0];
        String fileOut = args[1];
        UI userIn;
        
        IndexTree tree = new IndexTree();
        try{
            //read file into binary tree
            IOParser parser = new IOParser(fileIn);
            parser.indexTextFile(tree);
            
            //allow user to search for specific words
            userIn = new UI(tree);
            userIn.takeUserSearches();
            
            //write index to file
            parser.logIndex(tree, fileOut);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found. Verify file name and location.");
            System.out.println(e.toString());
        }
        catch(IOException e){
            System.out.println("Unknown difficulty handling file.");
            System.out.println(e.toString());
        }
        
    }
    
}
