/**
 * @author: Hitanshu Dudeja
 * Class: EECS 2500
 * Project 3: Video-Lookup-System
 * Date : 11/15/2020
 **/

public class HMap implements HMapInterface
{
    protected int size;                             // Keep track of elements in the HashMap
    protected LinkedListInterface<Movie> array[];   // The array that will have a linkedlist per each element to handle chaining
    private int lastTotNodesVisited;                // Last nodes visited in the LL, gets updated everytime we loop through one of the LLs


    HMap(int inputSize)
    {
        /**
         * This is the constructor for the HMap class
         * @param inputSize - the user will input the size for the array in the class
         * */

        array = new SinglyLinkedList[inputSize];    // Allocate memory for the given input size
        for (int i = 0; i < array.length; i++)      // Loop through each index of the array
            array[i] = new SinglyLinkedList<Movie>();   // Initialize a LL in each element of the array
        size = 0;                                   // We start with no movies/nodes in any LL so we set size to 0
        lastTotNodesVisited = 0;                    // Updates everytime we look for nodes in a LL
    }

    protected int hash(int hashKey)
    {
        return hashKey % array.length;              // Calculate the index location, depending on the size of the array, for the given hashkey
    }

    protected int hash(String key) {
        /**
         * This method relies on the Movie class function getHashKey to create a hash value for the given string key.
         * After getting the hashKey value for the given string, it hashes it to a number between 0 and the size of the
         * HMap's array
         *
         * @param key - Unique key that we are looking for in the HMap
         * @return the hash location where the value of the key would be stored in the HMap
         **/

        int hashKey = new Movie(key, 0, 0).getHashKey();    // Get the hashkey equivalent for the string key
        return hash(hashKey);
    }

    @Override
    public boolean containsKey(String key) {
        int index = hash(key);                  // Get the index location of where the movie would be stored
        LinkedListInterface<Movie> LL = array[index];  // Get the LinkedList where the movie would be stored
        lastTotNodesVisited = LL.getLastSearchResult(); // Update all the nodes searched for this
        return LL.isFound(key);                 // Return true if the key exists, else returns false
    }

    @Override
    public boolean containsValue(Movie value) {
        String key = value.getTitle();          // THe title of the movie is the key
        int index = hash(value.getHashKey());   // Get the index location where the movie should be saved
        LinkedListInterface<Movie> LL = array[index];  // Get the LL of the index location
        lastTotNodesVisited = LL.getLastSearchResult(); // Update all the nodes searched for this
        return (LL.isFound(key));               // Returns true if the movie is saved in the LL, else return false
    }

    @Override
    public Movie getValue(String key) {
        int index = hash(key);                  // Get the index location for the given key
        LinkedListInterface<Movie> LL = array[index];  // Get the element of the index
        lastTotNodesVisited = LL.getLastSearchResult(); // Update all the nodes searched for this
        return LL.getValue(key);                // Return the movie if found, else returns null
    }

    @Override
    public boolean isEmpty() {
        return (getSize() == 0);                // If the size is 0 then return true, as it's empty, else return false as it has movies saved
    }

    @Override
    public Movie removeValue(String key) {
        int index = hash(key);                              // Get the index location if the given key
        LinkedListInterface<Movie> LL = array[index];       // Get the LL for that index location
        lastTotNodesVisited = LL.getLastSearchResult(); // Update all the nodes searched for this
        Movie movie = LL.remove(key);                       // Remove movie from LL and return it.
        if (movie != null)                                  // If we actually find the movie in the hashmap/LL'
            size--;                                         // We update the size
        return movie;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean insert(String key, Movie value) {
        int index = hash(value.getHashKey());           // Get the index location which this value would be saved in the array
        LinkedListInterface<Movie> LL = array[index];   // Get the linked list where the movie could be inserted
        lastTotNodesVisited = LL.getLastSearchResult(); // Update all the nodes searched for this
        boolean didInsert = LL.insert(key, value);      // Try inserted into the LL and save the result
        if (didInsert)                                  // If the key was not found and we were able to insert the key and movie
            size++;                                     // Then we update the sizes
        else
            System.out.println("testing");
        return didInsert;                               // Returns true if value was inserted, meaning there wasn't a node in the LL with the given key
    }

    @Override
    public void printMap() {
        /**
         * Method iterates through every LinkedList in the array, and prints all the Movies saved in the current LL
        * */

        for (LinkedListInterface<Movie> LL: array)      // Iterate through every LL
            LL.printLL();                               // Print LL, the LL class has a print that iterates through it's noddes
    }

    @Override
    public int getMapArrLen() {
        return array.length;                            // Return the size of the array of the hashmap (this was initially provided for the constructor)
    }

    @Override
    public int getLastSearchResult() {
        return lastTotNodesVisited;
    }
}
