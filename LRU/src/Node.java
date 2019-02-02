/**
 * This is node class that you should use in your double
 * You should not modify this node class.
 * Linked list.
 */
public class Node{
    public Node previous;
    public Node next ;
    public int value;
    public int key;

    /**
     * Simple Constructor
     */
    public Node(int key, int value){
        this.value = value;
        this.key = key;
    }

    /**
     * toString method to assist in debugging
     */
    public String toString() {
        return "key = " + key + ", val = " + value;

    }

}
