/*
 * Test assign7 package for correct interation of small pieces.
 */


import assign7_Shmuel_Jacobs.IndexEntryNode;
import assign7_Shmuel_Jacobs.IndexTree;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shmuel Jacobs
 */
public class IndexTreeTests {
    
    private IndexTree tree;
    
    public IndexTreeTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tree = new IndexTree();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void makePrintNode(){
        ArrayList places = new ArrayList<Integer>(10);
        for(int i = 1; i < 5; i++){
            places.add( (8*i)%11 );
        }
        tree.addListing("Argument", places);
        
        System.out.println(tree.toString());
    }
    
    /*
    @Test
    public void addListingsPrintSave() throws IOException{
        //IndexEntryNode up, dup, low, mid;
        ArrayList<Integer> places = new ArrayList<>();
        
        for(int i = 1; i < 7; i++){
            places.add(i);
        }
        //up = new IndexEntryNode("up", places);
        tree.addListing("up", places);
        
        places.remove(new Integer(3));
        places.remove(new Integer(5));
        
        tree.addListing("low", places);
        
        places = new ArrayList();
        for(int i = 0; i < 4; i++){
            places.add(2*i%3);
        }
        
        tree.addListing("up", places);
        tree.addListing("mid", places);
        
        System.out.println(tree.toString());
        boolean logged = tree.logIndex();
        if(logged){
            System.out.println("Log Saved");
        }
        
    } */
    
    @Test
    public void canFindRootChildAbsent(){
        //Empty tree returns null for any search
        tree.addListing(null);
        IndexEntryNode found = tree.find("Alpha");
        assertEquals(null, found);
        
        //find root of tree
        ArrayList places = new ArrayList<Integer>();
        IndexEntryNode insertion = new IndexEntryNode("mid", places);
        tree.addListing("mid", places);
        found = tree.find("mid");
        assertTrue(found.equals(insertion));
        
        //find left child (also checks if case is handled)
        insertion = new IndexEntryNode("Alpha", places);
        tree.addListing(insertion);
        found = tree.find("alpha");
        assertTrue(found.equals(insertion));
        
        //still doesn't return absent
        found = tree.find("up");
        assertEquals(null, found);
    }
    
    @Test
    public void howDoesTrimWork(){
        String wordy = "up  ";
        String tight = wordy.trim();
        System.out.println(wordy + '|');
        System.out.println(tight + '|');
        if(wordy.equals(tight)){
            System.out.println("Trim is mutator.");
        }
    }
}
