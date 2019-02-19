public class BirdScooters {

    Node root;

    /**
     * Constructor
     */
    public BirdScooters() {
        root = null;
    }

    /**
     * Adds a scooter store at the Node passed in
     *
     * @param scooter - location of the scooter store
     * @return true if added
     */
    public boolean add(Node scooter) {
        // TODO implement this yourself
        return add(scooter, root);
    }

    private boolean add(Node scooter, Node itr){
        if (itr.equals(scooter)) return false;
        if (itr == null) {
            itr = scooter;
            return true;
        }
        else if (Node.compare(scooter, itr, itr.level) < 0) add(scooter, itr.left);
        else if (Node.compare(scooter, itr, itr.level) > 0) add(scooter, itr.right);
        return true;
    }
    /**
     * Find the closest scooter to the provided location
     *
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    public Node closestPoint(Node location) {
        // TODO implement this yourself
        if (Node.compare(location, root, 0) < 0) return closestPoint(location, root.left, root);
        else if (Node.compare(location, root, 0) > 0) return closestPoint(location, root.right, root);
        else return root;
    }

    private Node closestPoint(Node location, Node itr, Node champion) {
        if (itr == null) return champion;
        else if (Node.compare(location, itr, itr.level) < 0){
            if (Distance(itr, location) < Distance(champion, location)) champion = itr;
            return closestPoint(location, itr.left, champion);
        }
        else if (Node.compare(location, itr, itr.level) > 0){
            if (Distance(itr, location) < Distance(champion, location)) champion = itr;
            return closestPoint(location, itr.left, champion);
        }
        return champion;
    }
    /***************************************************************************
     *  Helper function for Distance Formula
     ***************************************************************************/

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]) {

    }
}

