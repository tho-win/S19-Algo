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

    static int maxPlayers;
    static int maxSum;
    static int sumTotal;
    static long numWays;
    static long numWay[][][];

    /**
     *
     * @param ranks The ranks of the players.
     * @return the number of ways to partition the candidates into two fair teams
     */
    public static long numWays(List<Integer> ranks) {
        maxPlayers = (int)Math.ceil((double)ranks.size()/2);
        sumTotal = (int)(double)((ranks.size()*(ranks.size()+1))/2);
        maxSum = (int)Math.ceil((double)sumTotal/2);
        numWay= new long[maxSum+1][maxPlayers+1][ranks.size()+1];
        for (int i= 0; i<= maxSum; i++){
            for (int j= 0; j <= maxPlayers; j++){
                for (int k= 0; k <= ranks.size(); k++){
                    numWay[i][j][k]= Long.MAX_VALUE;
                }
            }
        }
        numWays= solve(0, 0, ranks.size());
        if (ranks.size() % 2 == 0) return numWays;
        return numWays*2;
    }

    private static long solve(int sumA, int numA, int ranks) {
        if (sumA > maxSum || numA > maxPlayers || ranks < 0) {
            return 0;
        }
        if (numWay[sumA][numA][ranks] != Long.MAX_VALUE) return numWay[sumA][numA][ranks];
        else {
            if ((numA == maxPlayers) && sumTotal % 2 == 1) {//base case if sumTotal is odd
                if (sumA == maxSum || sumA == maxSum - 1) {
                    return 1;
                }
            }
            if ((numA == maxPlayers) && sumTotal % 2 == 0) {//base case if sumTotal is even
                if (sumA == maxSum) {
                    return 1;
                }
            }
            long tmp1= solve(sumA+ranks, numA+1, ranks-1);
            if (sumA+ranks <= maxSum && numA+1 <= numA && ranks-1 >= 0) numWay[sumA+ranks][numA+1][ranks-1]= tmp1;
            long tmp2= solve(sumA, numA, ranks-1);
            if (sumA <= maxSum && numA <= numA && ranks-1 >= 0) numWay[sumA][numA][ranks-1]= tmp2;
            return tmp1+tmp2;
        }
    }
/*
    private static boolean solve(int sumA, int numA, int ranks){

        if (sumA > maxSum || numA > maxPlayers  || ranks < 0){
            return false;
        }

        if (isValid[sumA][numA][ranks] == 1){
            //System.out.println("dup 1");
            return true;
        }

        if (isValid[sumA][numA][ranks] == -1){
            //System.out.println("dup -1");
            return false;
        }

        else {

            if ((numA == maxPlayers) && sumTotal % 2 == 1) {//base case if sumTotal is odd
                if (sumA == maxSum || sumA == maxSum - 1) {
                    isValid[sumA][numA][ranks] = 1;
                    return true;
                }
            }
            if ((numA == maxPlayers) && sumTotal % 2 == 0) {//base case if sumTotal is even
                if (sumA == maxSum) {
                    isValid[sumA][numA][ranks] = 1;
                    return true;
                }
            }

            for (; ranks > 0; ranks--) {
                if (solve(sumA + ranks, numA + 1, ranks - 1)) {
                    numWays++;
                    isValid[sumA][numA][ranks] = 1;
                }
                else if (sumA+ranks <= maxSum && numA+1 <= maxPlayers && ranks-1 > 0) {
                    isValid[sumA+ranks][numA+1][ranks-1] = -1;
                }
            }
/*
            if (!solve(sumA + ranks, numA + 1, ranks - 1)) {
                if (sumA <= maxSum && numA <= maxPlayers && ranks > 0) {
                    isValid[sumA+ranks][numA+1][ranks-1] = -1;
                }
            }

        }
        return false;
    }
*/
    /*private static boolean solve(List<Integer> ranks, int i, int numA, int sumA){
        if (maxPlayers - numA > ranks.size()-numA) return false;
        if (numA < maxPlayers/8 && i > maxPlayers/2) return false;
        if (numA < maxPlayers/4 && sumA > maxSum/2) return false;
        if (numA > maxPlayers) return false ;
        if (sumA > maxSum) return false;
        if ((numA == maxPlayers) && sumTotal%2 == 1){//base case if sumTotal is odd
            if (sumA == maxSum || sumA == maxSum - 1) {
                return true;
            }
        }
        if ((numA == maxPlayers) && sumTotal%2 == 0){//base case if sumTotal is even
            if (sumA == maxSum) {
                return true;
            }
        }

        for (; i < ranks.size(); i++){
            if (solve(ranks, i+1, numA+1, sumA+ranks.get(i))) numWays++;
        }
        return false;
    }

*/
/*
    private static void solve(List<Integer> ranks, int i){
        if (teamA.size() < maxPlayers/8 && i > maxPlayers/2 ) return;
        if (teamA.size() > maxPlayers) return;
        if (sumA > maxSum) return;
        if ((teamA.size() == maxPlayers) && sumTotal%2 == 1){//base case if sumTotal is odd
            if (sumA == maxSum || sumA == maxSum - 1) {
                numWays++;
                if (pick) {
                    teamAPick = new ArrayList<>(teamA);
                    pick = false;
                }
            }
            return;
        }
        if ((teamA.size() == maxPlayers) && sumTotal%2 == 0){//base case if sumTotal is even
            if (sumA == maxSum) {
                numWays++;
                if (pick) {
                    teamAPick = new ArrayList<>(teamAPick);
                    pick = false;
                }
            }
            return;
        }


        for (; i < ranks.size(); i++){

            if (!teamA.isEmpty()) {

                //while ((teamA.contains(ranks.get(i)) || ranks.get(i) < teamA.get(0)) && i < ranks.size()){
                while (teamA.contains(ranks.get(i)) && i < ranks.size()){
                    i++;
                    if (i == ranks.size()) return;
                }
            }
            Integer rank = ranks.get(i);
            teamA.add(rank);
            sumA += (int)rank;
            //System.out.println("in loop " +teamA);
            solve(ranks, i+1);
            sumA -= (int)rank;
            teamA.remove(rank);
        }
        return;
    }
    */

    /**
     *
     * @param ranks The ranks of the candidates
     * @param player An index into ranks for the player we deciding team for
     * @return false if Team A picks player, true if Team B does.
     *
     */
    public static boolean pick(List<Integer> ranks, int player) {
        double difference= Math.ceil((double)ranks.size()/2);
        //System.out.println("diff "+difference);
        double overhead= difference/2;
        //System.out.println("overhead "+overhead);
        int playerRank= ranks.get(player);
        if (ranks.size() % 2 == 1){
            if (overhead == (int)overhead && overhead % 2 == 0){
                if (playerRank % 2 == 1 && playerRank <= ranks.size() - overhead*2) return true;
                if (playerRank % 2 == 0 && playerRank > ranks.size() - overhead*2) return true;
                return false;
            }
            if (Math.floor(overhead) % 2 == 0){
                if (playerRank % 2 == 1 && playerRank != ranks.size()) return true;
                if (playerRank == ranks.size() - Math.ceil(overhead)) return true;
                return false;
            }
            else {
                if (playerRank % 2 == 1 && playerRank != ranks.size()) return true;
                if (playerRank == ranks.size() - Math.floor(overhead)) return true;
                return false;
            }

        }
        else {
            if (overhead == (int)overhead && overhead % 2 == 0){
                if (playerRank % 2 == 1 && playerRank > ranks.size() - overhead*2) return true;
                if (playerRank % 2 == 0 && playerRank <= ranks.size() - overhead*2) return true;
                return false;
            }
            if (Math.floor(overhead) % 2 == 0) {
                if (playerRank % 2 == 1 && playerRank != ranks.size() - Math.ceil(overhead)) return true;
                if (playerRank == ranks.size()) return true;
                return false;
            }
            else {
                if (playerRank % 2 == 1 && playerRank != ranks.size() - Math.floor(overhead)) return true;
                if (playerRank == ranks.size()) return true;
                return false;
            }

        }
        //return false;
    }


    public static void main(String args[]) {
        System.out.println("Welcome to Max's bonus problem!");
    }
}