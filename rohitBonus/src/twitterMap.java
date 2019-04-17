import sun.awt.image.ImageWatched;

import java.util.*;

public class twitterMap {
    private int numberOfPeople;
    private int follows;
    private LinkedList<Integer> twitterScrapeResults[];
    private boolean marked[];
    //private Stack<Integer> reversepostorder;

    /**
     * The constructor of the twitter map
     * Takes in the number of people and creates a corresponding adjency matrix.
     * @param numberOfPeople
     */
    twitterMap(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        twitterScrapeResults = new LinkedList[numberOfPeople];
        marked = new boolean[numberOfPeople];
        //reversepostorder = new Stack<>();
        this.follows = 0;
        for (int i = 0; i< numberOfPeople; ++i) twitterScrapeResults[i] = new LinkedList();
    }

    /**
     * This function adds the personBeingFollowed to the list of people the follower is following. It does this by going
     * to the twitterScrapeResults adjacency matrix of the follower and adds the integer representation of
     * personBeingFollowed to that matrix.
     * @param follower
     * @param personBeingFollowed
     */
    public void addFollower(int follower, int personBeingFollowed) {
        twitterScrapeResults[follower].add(personBeingFollowed);
    }

    /**
     * This returns a list of the integers that represent the people required to tweet to everyone.
     * TODO: Implement this method using whatever strategy you want. Runtime should be O(V + E).
     * TODO: Have fun and good luck!
     */
    public List<Integer> findKPeople() {
        List<Integer> answer = new ArrayList<Integer>();
        //run topological sort to find first influencer
        int head = -1;
        for (int i = 0; i < numberOfPeople; i++){
            if (!marked[i]) dfs(i, twitterScrapeResults);
            head = i;
        }

        marked = new boolean[numberOfPeople];
        //find circle of influencer from head with SCC, whomever can be reached from head in reverse map is in same SCC
        dfs(head, reverse(twitterScrapeResults));
        for (int i = 0; i < numberOfPeople; i++){
            if (marked[i]) answer.add(i);
        }


        /* this works but take very long time
        marked = new boolean[numberOfPeople];
        //find strongly connected component on head of topological sort
        int id[] = new int[numberOfPeople];
        int component = 0;
        //phase 1: run dfs on G reverse
        Stack<Integer> rp_r= new Stack<>();
        for (int i = 0; i < numberOfPeople; i++){
            if (!marked[i]) dfs(i, reverse(twitterScrapeResults), rp_r);
        }

        //phase 2: run dfs on G with reverse postorder

        marked = new boolean[numberOfPeople];
        while (!rp_r.empty()){
            int v = rp_r.pop();
            if (!marked[v]) dfs(v, twitterScrapeResults, id, component);
            component++;
        }

        for (int i = 0; i < id.length; i++){
            if (id[i] == id[head]) answer.add(i);
        }
        */
        return answer;
    }

    private void dfs(int v, LinkedList<Integer>[] G){
        marked[v] = true;
        for (int w : G[v])
            if (!marked[w]) dfs(w, G);
    }

    private void dfs(int v, LinkedList<Integer>[] G, int[] id, int component){
        marked[v] = true;
        id[v] = component;
        for (int w : G[v]){
            if (!marked[w]) dfs(w, G, id, component);
        }

    }
    /**
     * added to compile
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("Welcome to Rohit's bonus problem!");
    }

    private LinkedList<Integer>[] reverse(LinkedList<Integer>[] G) {
        LinkedList<Integer> reverse[] = new LinkedList[G.length];
        for (int i = 0; i< G.length; ++i) reverse[i] = new LinkedList();
        for (int v = 0; v < G.length; v++) {
            for (int w : G[v]) {
                reverse[w].add(v);
            }
        }
        return reverse;
    }
}

