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
 *
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
    //TODO: Trim off all non-characters
    public void indexTextFile(IndexTree index) throws IOException {
        String[] wordsInLine;
        String textLine = fileRead.readLine();
        //visit every line in the document
        for (lineNumber = 1; textLine != null; lineNumber++) {
            wordsInLine = textLine.split(" ");
            //for each word
            for (String word : wordsInLine) {
                //validate word and remove non-chars
                word = word.trim();//wrong method--remove punctuation
                IndexEntryNode listing = index.find(word);
                if (listing == null) {//word not in index yet--add
                    ArrayList onePlace = new ArrayList<Integer>();
                    onePlace.add(lineNumber);
                    index.addListing(word, onePlace);
                } else //add this line number to its listing
                {
                    index.find(word).appendPlaces(lineNumber);
                }
            }
            textLine = fileRead.readLine();

        }
        fileRead.close();
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
