/* 
 * CIS2168 006 Data Structures Spring 2017
 * Shmuel Jacobs shmuel.jacobs@temple.edu
 * Assignment 7
 * UI class
 * Scanner reads user input, handles (searches tree)
 */
 package assign7_Shmuel_Jacobs;
 
/**
 * @author Shmuel Jacobs
 */
public class UI{
    //user input scanner
    private static Scanner userIn;
    //class calling constructor will provide an index, in BST form
    private static IndexTree index;
    
    private static final String BEGINSEARCHMSG =
        "To search for a single word in the index," +
        "type the word followed by a return.";
        
    private static final String EACHSEARCHMSG = 
        "Enter the search term or \':q\' to exit search and write index.";
        
    private static final String NOTFOUNDMSG =
        "Search target absent from indexed file.";
    
    /**
     * Constructor. Calling class must provide index.
     * @param tree Index in BST form for UI to call searches in.
     */
    UI(IndexTree tree){
        index = tree;
        
        userIn = new Scanner(System.in);
    }
    
    /**
     * Allow user to type in words to see index listing, if it exists.
     */
    protected void takeUserSearches(){
        //user prompts
        System.out.println(BEGINSEARCHMSG);
        System.out.println(EACHSEARCHMSG);
        
        //get user input
        String userEntered = userIn.nextLine();
        
        //until user types ":q"...
        while(!userEntered.equals(":q")){
            //get listing with the word he wants
            IndexEntryNode entry = tree.find(userEntered);
            //case: it's not here, so print "not found" message
            if(entry == null){
                System.out.println(NOTFOUNDMSG);
            } else {//case: found it, here it is
                System.out.println(entry.toString());
            }
            
            //prompt user and get next search
            System.out.println(EACHSEARCHMSG)
            userEntered = Scanner.nextLine();
        }
        
    }
}