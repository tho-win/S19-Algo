import java.util.*;

public class RoadTrip {

    private final List<City> cities;
    private final List<Road> roads;
    private int[][] stateTolls; // toll for x, y would be states[x][y]. Eg. toll for 01 is states[0][1]
    private City[] cityTo;
    private LinkedList<Road>[] adj;
    private double[] tollTo;
    private Queue<Road> pq;

    public RoadTrip(Graph graph, int[][] stateTolls) {
        this.cities = new ArrayList<City>(graph.getCities());
        this.roads = new ArrayList<Road>(graph.getRoads());
        this.stateTolls = stateTolls;
        //initialize cityTo to null
        this.cityTo= new City[cities.size()];
        for (int i= 0; i < cityTo.length; i++) cityTo[i]= null;
        //initialize adj
        this.adj= new LinkedList[cities.size()+1];
        for (int i= 0; i < adj.length; i++) adj[i]= new LinkedList();
        //initialize toll to every city as infinity
        this.tollTo= new double[cities.size()+1];
        for (int i= 0; i < tollTo.length; i++) tollTo[i]= Double.MAX_VALUE;

        this.pq= new PriorityQueue();

        //set road's toll
        for (Road r:roads){
            if (r.getSource().equals(r.getDestination())) r.setTollCost(0);
            else r.setTollCost(stateTolls[(int)Math.floor(r.getDestination().getCoords().get(0))]
                    [(int)Math.floor(r.getDestination().getCoords().get(1))]);
            //build adj list
            adj[r.getSource().getIntId()].add(r);
        }
    }


    public void computeCostsFromSource(City source) {
        // TODO
        tollTo[source.getIntId()]= 0;
        cityTo[source.getIntId()]= source;
        for (Road r: adj[source.getIntId()]) pq.add(r);
        while (!pq.isEmpty()){
            Road r = pq.poll();
            relax(r);
        }
        //for (City c: cityTo) System.out.print(c + ", ");
    }

    private void relax(Road r){
        int s= r.getSource().getIntId();
        int t= r.getDestination().getIntId();
        if (tollTo[t] > tollTo[s] + r.getTollCost()){
            tollTo[t] = tollTo[s] + r.getTollCost();
            cityTo[t] = r.getSource();
            for (Road road: adj[t]){
                pq.add(road);
            }
        }
    }


    public LinkedList<City> getPath(City target) {
        // TODO
        LinkedList<City> path = new LinkedList<City>();
        path.addFirst(target);
        int t= target.getIntId();
        while (t != cityTo[t].getIntId()){
            path.addFirst(cityTo[t]);
            t = cityTo[t].getIntId();
        }
        //for (City c: path) System.out.print(c + ", ");
        return path;
    }

}
