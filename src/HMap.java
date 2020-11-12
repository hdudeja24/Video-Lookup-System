public class HMap implements HMapInterface
{
    private int size;                       // Keep track of elements in the HashMap
    LinkedListInterface<String, Movie> array[];

    HMap(int inputSize)
    {
        array = new SinglyLinkedList[inputSize];
        size = 0;
    }

    protected int hash(int hashKey)
    {
        return hashKey % array.length;
    }

    protected int hash(String key) {
        String words[] = key.split(" ");
        int hashKey = 0;
        for (String word : words)
            hashKey += word.charAt(0);          // Add the value of the first letter in the current word

        return hash(hashKey);
    }

    @Override
    public boolean containsKey(String key) {
        int index = hash(key);                  // Get the index location of where the movie would be stored
        LinkedListInterface<String, Movie> LL = array[index];  // Get the LinkedList where the movie would be stored
        return LL.isFound(key);                 // Return true if the key exists, else returns false
    }

    @Override
    public boolean containsValue(Movie value) {
        String key = value.getTitle();          // THe title of the movie is the key
        int index = hash(value.getHashKey());   // Get the index location where the movie should be saved
        LinkedListInterface<String, Movie> LL = array[index];  // Get the LL of the index location
        return (LL.isFound(key));               // Returns true if the movie is saved in the LL, else return false
    }

    @Override
    public Movie getValue(String key) {
        int index = hash(key);                  // Get the index location for the given key
        LinkedListInterface<String, Movie> LL = array[index];  // Get the element of the index
        return LL.getValue(key);                // Return the movie if found, else returns null
    }

    @Override
    public boolean isEmpty() {
        return (getSize() == 0);
    }

    @Override
    public Movie removeValue(String key) {
        int index = hash(key);              // Get the index location if the given key
        LinkedListInterface<String, Movie> LL = array[index];       // Get the LL for that index location
        Movie movie = LL.remove(key);                               // Remove movie from LL and return it.
        if (movie != null)                                          // If we actually find the movie in the hashmap/LL'
            size--;                                                 // We update the size
        return LL.remove(key);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean insert(String key, Movie value) {
        int index = hash(value.getHashKey());           // Get the index location which this value would be saved in the array
        LinkedListInterface LL = array[index];          // Get the linked list where the movie could be inserted
        boolean didInsert = LL.insert(key, value);      // Try inserted into the LL and save the result
        if (didInsert)                                  // If the key was not found and we were able to insert the key and movie
            size++;                                     // Then we update the sizes
        return didInsert;                               // Returns true if value was inserted, meaning there wasn't a node in the LL with the given key
    }

    @Override
    public void printMap() {
        /**
         * Method iterates through every LinkedList in the array, and prints all the Movies saved in the current LL
        * */
        for (LinkedListInterface LL: array)             // Iterate through every LL
            LL.printLL();                               // Print LL
    }
}
