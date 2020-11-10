public interface LinkedListInterface <Key,Value> {
    public boolean insert(Key key, Value value);

    public boolean isEmpty();           // If the LL is empty return true, else return false

    public boolean isFound(Key key);  // If the item is in the list

    public int size();                  // Return the current size of the LL

    public Value remove(Key key);   // If the item is in the LL, we remove and return true. Else item not in the LL return false as we can't remove

    public void printLL();              // Iterate and print the values inside the LL
}
