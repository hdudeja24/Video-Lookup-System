public interface LinkedListInterface <Key,Value> {
    boolean insert(Key key, Value value);

    boolean isEmpty();          // If the LL is empty return true, else return false

    boolean isFound(Key key);   // If the item is in the list

    int size();                 // Return the current size of the LL

    Value remove(Key key);      // If the item is in the LL, we remove and return true. Else item not in the LL return false as we can't remove

    void printLL();             // Iterate and print the values inside the LL

    Value getValue(Key key);    // Return node's value that matches the given key
}
