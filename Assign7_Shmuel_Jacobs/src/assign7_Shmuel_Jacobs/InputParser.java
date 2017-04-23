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
        String[] textLine = fileRead.readLine().split(" ");
        //visit every line in the document
        for (lineNumber = 1; textLine != null; lineNumber++) {
            //for each word
            for (String word : textLine) {
                //validate word and remove non-chars
                word.trim();//wrong method--remove punctuation
                IndexEntryNode listing = index.find(word);
                if (listing == null) {//word not in index yet--add
                    index.addListing(word);
                } else {//word has been indexed--add this line number to its listing
                    listing.appendPlaces(lineNumber);
                }
            }
        }
        fileRead.close();
    }
}
