import java.util.*;

/**
 *
 * See assignment details on AutoGradr.
 *
 * Enjoy!
 *  - Max
 *
 */
public class Draft {

    /**
     *
     * @param ranks The ranks of the players.
     * @return the number of ways to partition the candidates into two fair teams
     */
    public static long numWays(List<Integer> ranks) {
        Collections.sort(ranks);
        int totalPlayers = ranks.size();
        int sumRanks = totalPlayers*(totalPlayers+1)/2;
        int targetSum = sumRanks/2;
        int maxPlayers = totalPlayers/2;
        int numWays = 0;
        int sum = 0;
        int i = 0;
        while (true){
            while ((sum += ranks.get(i++)) < targetSum)
                if (i >= maxPlayers) break;
        }

        return numWays;
    }

    private static long numWays(List<Integer> ranks, int targetSum, int maxPlayers, int i){
        if (i == maxPlayers-1) return ranks.get(i);
        return 
        }
    }
    /**
     *
     * @param ranks The ranks of the candidates
     * @param player An index into ranks for the player we deciding team for
     * @return false if Team A picks player, true if Team B does.
     *
     */
    public static boolean pick(List<Integer> ranks, int player) {
        return false;
    }

    public static void main(String args[]) {
        System.out.println("Welcome to Max's bonus problem!");
    }

}