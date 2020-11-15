/**
 * @author: Hitanshu Dudeja
 * Class: EECS 2500
 * Project 3: Video-Lookup-System
 * Date : 11/15/2020
 **/

public interface LinkedListInterface <Value> {
    boolean insert(String key, Value value);

    boolean isEmpty();          // If the LL is empty return true, else return false

    boolean isFound(String key);   // If the item is in the list

    int size();                 // Return the current size of the LL

    Value remove(String key);      // If the item is in the LL, we remove and return true. Else item not in the LL return false as we can't remove

    void printLL();             // Iterate and print the values inside the LL

    Value getValue(String key);    // Return node's value that matches the given key

    int getLastSearchResult();      // Returns the last total nodes visited
}
