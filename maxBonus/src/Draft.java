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
        int sumRanks = 0;
        int totalPlayers = ranks.size();
        int numWays = 0;
        List<Integer> teamA;
        List<Integer> teamB;
        for (int i = 0; i < ranks.size(); i++){
            sumRanks += ranks.get(i);
        }

        return numWays;
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