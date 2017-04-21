/*
 * Test assign7 package for correct interation of small pieces.
 */

import assign7_Shmuel_Jacobs.IndexEntryNode;
import assign7_Shmuel_Jacobs.IndexTree;
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
    
    @Test
    public void addListingsPrintTree(){
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
    }
}
