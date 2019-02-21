
public class BirdScooters {

    Node root;

    /**
     * Constructor
     */
    public BirdScooters(){
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
        if (root == null){
            root = scooter;
            return true;
        }
        return root.add(scooter);
    }

    /**
     * Find the closest scooter to the provided location
     *
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    public Node closestPoint(Node location) {
        // TODO implement this yourself
        /*
        Node champion = null;
        if (Node.compare(location, root, 0) < 0) champion = closestPoint(location, root.left, root);
        else if (Node.compare(location, root, 0) > 0) champion = closestPoint(location, root.right, root);
        if (Distance(champion, location) < Distance(root, location)) return champion;
        else return root;
        */
        return closestPoint(location, root, root);
    }

    private Node closestPoint(Node location, Node itr, Node champion) {
        if (itr == null) return champion;
        if (itr.compare(location, itr, itr.level) <= 0) {
            if (Distance(itr, location) < Distance(champion, location)) champion = itr;
            champion = closestPoint(location, itr.left, champion);
            if (itr.level % 2 == 1 && (Math.abs(itr.y - location.y) < Distance(champion, location))) {
                champion = closestPoint(location, itr.right, champion);
            } else if (itr.level % 2 == 0 && (Math.abs(itr.x - location.x) < Distance(champion, location))) {
                champion = closestPoint(location, itr.right, champion);
            }
        }
        else if (itr.compare(location, itr, itr.level) > 0) {
            if (Distance(itr, location) < Distance(champion, location)) champion = itr;
            champion = closestPoint(location, itr.right, champion);
            if (itr.level % 2 == 1 && (Math.abs(itr.y - location.y) < Distance(champion, location))) {
                champion = closestPoint(location, itr.left, champion);
            } else if (itr.level % 2 == 0 && (Math.abs(itr.x - location.x) < Distance(champion, location))) {
                champion = closestPoint(location, itr.left, champion);
            }
        }
        return champion;
    }

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]){
    }

}

