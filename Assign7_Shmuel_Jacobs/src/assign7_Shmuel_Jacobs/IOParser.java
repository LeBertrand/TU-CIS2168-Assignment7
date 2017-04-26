/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Input Parser
 * Class reads in text file, line by line, puts it into BST
 * provided in IndexEntryNode, and writes to to file.
 */
package assign7_Shmuel_Jacobs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Handles all application IO.
 * @author Shmuel Jacobs
 */
public class IOParser {

    //Reader will read file that program is indexing
    BufferedReader fileRead;
    //Current line will be added to places in listing, whereever found
    int lineNumber;

    /**
     * Constructor for file parser
     * @param fileName file to read in
     * @throws FileNotFoundException if bad file name or path
     */
    IOParser(String fileName) throws FileNotFoundException {
        fileRead = new BufferedReader(new FileReader(fileName));
    }

    /**
     * Read words from text file and load them into index tree.
     * @param index tree to fill with words from file
     * @throws IOException if fileRead is working on invalid file 
     */
    public void indexTextFile(IndexTree index) throws IOException {
        //String array for holding lines of text and String for individual words
        String[] wordsInLine;
        String textLine = fileRead.readLine();
        
        //visit every line in the document
        for (lineNumber = 1; textLine != null; lineNumber++) {
            
            wordsInLine = textLine.split(" ");
            
            //for each word
            for (String word : wordsInLine) {
                //TODO: test trim method
                //validate word and remove non-chars
                try{
                    word = trimEndPunct(word);
                    IndexEntryNode listing = index.find(word);
                    if (listing == null) {//word not in index yet--add
                        ArrayList onePlace = new ArrayList<Integer>();
                        onePlace.add(lineNumber);
                        index.addListing(word, onePlace);
                    } else { //add this line number to its listing
                        index.find(word).appendPlaces(lineNumber);
                    }
                }
                catch (IndexOutOfBoundsException e){}
            }
            textLine = fileRead.readLine();

        }
        fileRead.close();
    }
    
    /**
     * Remove all non-alphabetic characters from input string.
     * @param string String to remove all non-letters from
     * @return modified version of input string
     * @throws IndexOutOfBoundsException if input has no letters
     */
    public String trimEndPunct(String string) throws IndexOutOfBoundsException {
        
        StringBuilder word = new StringBuilder(string);
        while(!Character.isAlphabetic(word.charAt(0))){
            word.deleteCharAt(0);
        }
        
        while(!Character.isAlphabetic( word.charAt(word.length() - 1) ) ){
            word.deleteCharAt( word.length() - 1 );
        }
        
        string = word.toString();
        
        return string;
    }
    
    /**
     * Save contents of index tree to text file
     * @param tree index to write to file
     * @param fileOut name of output file
     * @return true if file was successfully written, false otherwise
     * @throws IOException
     */
    public boolean logIndex(IndexTree tree, String fileOut) throws IOException {
        boolean logged = false;
        try (PrintWriter writerOut = new PrintWriter(new FileWriter(fileOut))) {
            writerOut.println(tree.toString());
            writerOut.println("{Sys-time: " + System.currentTimeMillis() + '}');
            logged = true;
        } catch (FileNotFoundException e) {
            System.out.println("Problem with output file");
        }
        finally{
            return logged;
        }
    }
}
