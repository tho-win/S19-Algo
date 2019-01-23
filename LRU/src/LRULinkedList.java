public class LRULinkedList {
    public Node head;
    public Node tail;
    public int capacity =0;
    public int size =0;

    /**
     * Constructor creates a double linked list with a single value.
     */
    public LRULinkedList(int capacity){
        this.capacity = capacity;
    }

    /**
     * Add a node to the linked list.
     * @param key
     * @param value
     * @return new node
     */
    public Node add(int key, int value) {
        // TODO: implement this method
        Node temp = new Node(key, value);
        if (size == 0){
           // temp.next = null;
            //temp.previous = null;
            head = tail = temp;
            size++;
        }
        else {
            head.previous = temp;
            temp.next = head;
          //  temp.previous = null;
            head = temp;
            size++;
            if (size > capacity){
                tail = tail.previous;
                tail.next = null;
                size--;
            }
        }
        return head;
    }

    /**
     * Remove the last item in the linked list.
     */
    public void deleteTail(){
        // TODO: implement this method
        if (head == tail)
            head = tail = null;
        else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
    }

    /**
     * Get the node that corresponds to the passed in key.
     * @param key
     * @return node that matches key
     */
    public Node getNode(int key){
        // TODO: implement this method
        if(head == null){
            return null;
        }

        if(head.key == key){
            return head;
        }

        Node itr = head.next;
        while (itr != null){
            if (itr.key == key) {
                moveNodeToHead(itr);
                return itr;
            }
            itr = itr.next;
        }
        return itr;
    }

    /**
     * Move the passed in node to the head position.
     * @param node
     */
    public void moveNodeToHead(Node node){
        // TODO: implement this method
        if (node == head){
                return;
        }

        if (size == 1) return;

       /* if (tail == node){
            deleteTail();
        }
        else {
            node.next.previous = node.previous;
            node.previous.next = node.next;
            size --;
        }*/
        node.previous.next = node.next;
        if (node != tail) {
            node.next.previous = node.previous;
        }
        else tail = tail.previous;
        size--;
        add(node.key, node.value);

       /* node.next = head;
        head.previous = node;
        head = node;*/
    }

    /*public String toString(){
        Node itr = head;
        StringBuffer buff = new StringBuffer();
        while (itr != null){
            buff.append(itr.key);
            buff.append(" ");
            itr = itr.next;
        }
        return buff.toString();
    }*/

    public static void main(String[] args) {
    }
}
