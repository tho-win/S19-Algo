import java.util.Stack;

public class DepthFirstSearch {
    Stack<Integer> reversePostorder;
    boolean marked[];

    public DepthFirstSearch(Digraph G){
        reversePostorder = new Stack<>();
        marked = new boolean[G.V()];

        for (int v= 0; v < G.V(); v++){
            if (!marked[v]) dfs(G, v);
        }
    }

    public DepthFirstSearch(Digraph G, Stack<Integer> order, int[] id){
        reversePostorder = new Stack<>();
        marked = new boolean[G.V()];
        int component = 0;
        while (!order.empty()){
            int v = order.pop();
            //id[v] = component;
            if (!marked[v]) dfs(G, v, id, component);
            component++;
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]) dfs(G, w);
        }
        reversePostorder.push(v);
    }

    private void dfs(Digraph G, int v, int[] id, int component){
        marked[v] = true;
        id[v] = component;
        for (int w : G.adj(v)){
            if (!marked[w]) dfs(G, w, id, component);
        }
        reversePostorder.push(v);
    }

    public Stack<Integer> getReversePostorder(){ return this.reversePostorder; }
}
