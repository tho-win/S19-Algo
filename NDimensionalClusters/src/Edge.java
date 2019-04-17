public class Edge implements Comparable<Edge> {
    private final Point v;
    private final Point w;
    private final double weight;

    public Edge(Point v, Point w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Point either(){ return v;}

    public Point other(Point vertex){
        if (vertex == v) return w;
        else return v;
    }

    public double weight(){ return weight; }

    @Override
    public int compareTo(Edge other){
        return Double.compare(other.weight, this.weight);
    }

    public String toString(){
        return v+", "+w+", "+weight;
    }

}