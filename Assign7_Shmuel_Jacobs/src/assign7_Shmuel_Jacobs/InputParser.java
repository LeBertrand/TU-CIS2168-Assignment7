/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * Input Parser
 * Class reads in text file, line by line, and puts it into BST
 * provided in IndexEntryNode
 */
package assign7_Shmuel_Jacobs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Shmuel Jacobs
 */
public class InputParser {

    //Reader will read file that program is indexing
    BufferedReader fileRead;
    //Current line will be added to places in listing, whereever found
    int lineNumber;

    InputParser(String fileName) throws FileNotFoundException {
        fileRead = new BufferedReader(new FileReader(fileName));
    }

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
                word.trim();//wrong method--remove punctuation
                IndexEntryNode listing = index.find(word);
                if (listing == null) {//word not in index yet--add
                    ArrayList onePlace = new ArrayList<Integer>();
                    onePlace.add(1);
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
}
