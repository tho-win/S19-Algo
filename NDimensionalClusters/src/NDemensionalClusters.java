import java.util.*;

public class NDemensionalClusters {

    private double weight;
    private PriorityQueue<Edge> pq; //a max PQ
    private PriorityQueue<Edge> Mst; //a min PQ
    private Queue<Point> points;
    public int V;

    public NDemensionalClusters(){
        V = 0;
        points = new LinkedList<>();
    }
    /**
     * Adds a point to the dataSet being considered
     * @param point
     */
    public void add(Point point) {
        // TODO: Implement this method.
        points.add(point);
        V++;
    }

    /**
     * The Total sum on the edges in a cluster.
     * @param clusters the number of clusters to consider
     * @return sum
     */
    public double getSum(int clusters) {
        // TODO: Implement this method
        pq = new PriorityQueue<>();
        Mst = new PriorityQueue<>(Collections.reverseOrder());
        for (Point p: points) p.marked = false;
        weight = 0;
        visit(points.peek());
        //System.out.println("pq "+pq.toString());
        while (!pq.isEmpty() && Mst.size() < V - 1){
            Edge e = pq.poll();
            if (e.either().marked && e.other(e.either()).marked) continue;
            Mst.add(e);
            weight += e.weight();
            if (e.either().marked) visit(e.other(e.either()));
            if (e.other(e.either()).marked) visit(e.either());
        }

        //System.out.println("mst "+Mst.toString());
        //System.out.println("weight "+weight);
        double sum = weight;
        for (int i= clusters; i > 1; i--){
            sum = sum - Mst.poll().weight();
        }
        return sum;
    }

    private void visit(Point p){
        p.marked = true;
        for (Point point : points){
            if (point == p) continue;
            pq.add(new Edge(p, point, p.distance(point)));
        }
    }
    public static void main(String[] args) {

    }
}
