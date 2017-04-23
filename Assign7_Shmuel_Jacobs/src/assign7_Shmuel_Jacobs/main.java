/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign7_Shmuel_Jacobs;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author J
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = args[0];
        
        IndexTree tree = new IndexTree();
        try{
            InputParser parser = new InputParser(fileName);
            parser.indexTextFile(tree);
            tree.logIndex();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found. Verify file name and location.");
        }
        catch(IOException e){
            System.out.println("Unknown difficulty handling file.");
        }
        
    }
    
}
