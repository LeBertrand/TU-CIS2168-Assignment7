/*
 * Test assign7 package for correct interation of small pieces.
 */

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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void makePrintNode(){
        IndexTree tree = new IndexTree();
        ArrayList places = new ArrayList<Integer>(10);
        for(int i = 1; i < 5; i++){
            places.add( (8*i)%11 );
        }
        tree.addListing("Argument", 4, places);
        
        System.out.println(tree.toString());
    }
    
    @Test
    public void whatDoStringsDo(){
        String up = "up";
        String dup = "up";
        String low = "low";
        int ineq = up.compareTo(dup);
        System.out.println("Comparing duplicate Strings: " + ineq);
        ineq = up.compareTo(low);
        System.out.println("Comparing greater to lesser:" + ineq);
    }
}
