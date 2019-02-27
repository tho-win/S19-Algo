import java.util.ArrayList;
import java.util.Stack;

public class DDRRobot {

    public Digraph G = null;
    private int Dimension;

    /**
     * Initalizes the DDRobot Board
     * @param squares
     */
    public DDRRobot(int squares){
        // TODO: implement this method
        G = new Digraph(squares);
        Dimension = (int)Math.sqrt(squares);
    }

    /**
     * key:
     * → 1
     * ↑ 2
     * ← 3
     * ↓ 4
     * @param square  based o the square number
     * @param arrow based on tile arrow number
     */

    public void addTile(int square, int arrow){
        // TODO: implement this method
        switch (arrow){
            case 1:
                if (square == 0 || square % Dimension != 2) G.addEdge(square, square+1);
                break;
            case 2:
                if (square >= Dimension) G.addEdge(square, square-Dimension);
                break;
            case 3:
                if (square == 0 || square % Dimension != 0) G.addEdge(square, square-1);
                break;
            case 4:
                if (square == 0 || square < G.V()-Dimension) G.addEdge(square, square+Dimension);
                break;
        }
    }

    /**
     * Retuns the list of Tiles
     */
    public ArrayList<Integer> getPlayOptions(){
        // TODO: implement this method
        //phase 1
        DepthFirstSearch g_r = new DepthFirstSearch(G.reverse());
        //phase 2
        int id[] = new int[G.V()];
        DepthFirstSearch g = new DepthFirstSearch(G, g_r.getReversePostorder(), id);
        int[] componentSize = new int[G.V()];
        //get biggest component of play option
        int maxSize = 0;
        for (int i : id){
            componentSize[i]++;
            if (componentSize[i] > maxSize) maxSize = i;
        }
        ArrayList<Integer> option = new ArrayList<>();
        for (int i = 0; i < id.length; i++){
            if (id[i] == maxSize){
                option.add(i);
            }
        }
        return option;
    }


    public static void main(String[] args) {
        DDRRobot dBot = new DDRRobot(9);
        dBot.addTile(0, 1);
        dBot.addTile(1, 4);
        dBot.addTile(2, 1);
        dBot.addTile(3, 2);
        dBot.addTile(4, 3);
        dBot.addTile(4, 1);
        dBot.addTile(5, 4);
        dBot.addTile(6, 2);
        dBot.addTile(7, 1);
        dBot.addTile(7, 3);
        dBot.addTile(8, 2);
        System.out.print(dBot.G.toString());
    }

}