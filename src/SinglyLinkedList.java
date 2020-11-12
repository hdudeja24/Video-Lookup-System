public class SinglyLinkedList<Key,Value> implements LinkedListInterface<Key,Value> {
    private class Node{
        Key key;                             // Unique ID in the LL
        Value value;                         // The data of the node
        Node next;                          // Reference to the next node in the LL
        Node(Key key,Value value){          // Constructor given a Type item
            this.key = key;                 // Set the key as key of the node
            this.value = value;             // Set the value as value of the node
            next = null;                    // Set the next node reference as null
        }
    }

    // Global variables
    Node head, tail;            // The head for the start of the LL and tail for the end of LL
    int size;                   // Keep track of the size of the LL

    SinglyLinkedList(){         // Basic constructor for the LL class
        head = null;            // We start with an empty LL
        tail = null;            // We start with an empty LL
        size = 0;               // Set size to 0 as there is nothing in the LL
    }

    @Override
    public boolean insert(Key key, Value value) {
        Node insertNode = new Node(key, value);   // Create a new node with the given item

        if(isEmpty()){              // If the list is empty, the new node becomes the head
            head = tail = insertNode;
            size++;
            return true;
        }

        // Else the LL has at least one node, we iterate through the Linked List
        if (isFound(key))          // If the item is found already in the LL, we don't insert
            return false;           // Return false as we don't insert

        // We get here if the LL is not empty and the item is not in the LL
        tail.next = insertNode;     // Insert the new node after the current tail
        tail = insertNode;          // Set the new node as tail
        return true;                // Return true as we inserted the new node
    }


    private Node search (Key key){
        /**
         * Method iterates through the LL to find a node with the given item in it
         * @param item - the item we are looking for in the LL
         * @returns the Node with the given item in the LL, else return null if the item is not found in the LL
         *
         * */

        if (isEmpty())              // If the LL is empty
            return null;            // Then we return null as the item can't be in the LL
        // We get here if the LL is not empty, aka has at least one node in it
        Node remHead = head;        // Remember the head of the LL before iterating through it
        Node currNode = head;       // We'll use this to iterate and for better naming convention
        while (currNode != null)    // While we haven't fallen off the LL
        {
            if (currNode.key == key) // First we check if the current node has the item
            {
                head = remHead;     // Set the head back to the original position
                return currNode;    // We return the node as it has the item we are looking for
            }
            // We get here if the current node doesn't contain the item we are looking for
            currNode = currNode.next;   // We move to the next nodde in the LL
        }
        head = remHead;             // Set the head back to it's original position
        return null;                // We return null as we fell off the LL without finding a node with the given item
    }
    @Override
    public boolean isEmpty() {
        return (head == null && size == 0);
    }

    @Override
    public boolean isFound(Key key) {
        /**
         * Iterates through the LL to find the node with the given item.
         * @param item - Requested item to look for the LL
         * @returns true if a node with the item is found, else we return false
         **/

        return !(search(key) == null);
    }

    @Override
    public int size() {
        return this.size;               // Return the current size of the LL
    }

    @Override
    public Value remove(Key key) {
        /**
         * Method removes the requested item from the LL
         * @param item - requested item to be deleted from the LL
         * @returns false if the item is not in the LL or the LL is empty
         * @returns true if the item is in the LL and the node was successfully removed
         * */

        Node removeNode = search(key);      // Get the search result
        if (removeNode == null)             // If the node is null then we didn't find the item or the LL is empty
            return null;                    // We return false as there is nothing to remove
        // We get here if the removeNode is not null, therefore we have to remove it from the LL
        if (removeNode == head)             // First case, if the removeNode is the head
        {
            if (head == tail)               // If there is only one node in the LL, which is both head & tail
                head = tail = null;         // We set them both back to null
            else                            // Else the head and tail are not the same
                head = head.next;           // We make the head's next node the new head
        }
        else if (removeNode == tail)        // If the removeNode is the tail, aka last node in the LL
        {
            // We need to get the node before the tail in order to make it the new tail
            Node prevToTail = head;         // We start from the head to iterate
            while (prevToTail.next != tail) // While the node's next node is not tail we continue
                prevToTail = prevToTail.next;   // Move to the next node
            tail = prevToTail;              // Make the prev node the new tail
            tail.next = null;               // Remove the next node from the new tail
        }
        else                                // Else the removeNode is not the head or tail
        {
            // We need to iterate to get the prevNode of the removeNode
            Node prevNode = head;           // We start from the head to iterate
            while (prevNode.next != removeNode) // We iterate until we get to the previous node of the removeNode
                prevNode = prevNode.next;   // Move to the next node
            // We get here when we get the previous node of the removeNode
            prevNode.next = removeNode.next;    // We set the prevNode's next node to be the removeNode's next node
        }
        return removeNode.value;
    }

    @Override
    public void printLL() {
        Node remHead = head;                    // Remember the head of the LL before iterating
        while (head != null)
        {
            System.out.println(head.value);     // Print the value of the node
            head = head.next;                   // Move to the next node
        }
        head = remHead;                         // Set the head back to the original position
    }

    @Override
    public Value getValue(Key key) {
        Node searchNode = search(key);          // Search and get the node that matches the given key
        if (searchNode == null)                 // If the searchNode is null that means there is no node with the given key
            return null;                        // So we return null again
        else                                    // Else we found a node with the given key
            return searchNode.value;            // Return the node's value
    }


}
