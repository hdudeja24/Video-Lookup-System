public interface LinkedListInterface <Type> {
    public boolean insert(Type item);

    public boolean isEmpty();               // If the LL is empty return true, else return false

    public boolean isFound(Type item);      // If the item is in the list

    public int size();                      // Return the current size of the LL

    public boolean remove(Type item);     // If the item is in the LL, we remove and return true. Else item not in the LL return false as we can't remove
}
