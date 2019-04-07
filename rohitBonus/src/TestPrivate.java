

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;


public class TestRunner {

    private class solution {
        private int numberOfPeople;
        private LinkedList<Integer> twitterScrapeResults[];

        solution(int numberOfPeople) {
            this.numberOfPeople = numberOfPeople;
            twitterScrapeResults = new LinkedList[numberOfPeople];
            for (int i = 0; i< numberOfPeople; ++i)
                twitterScrapeResults[i] = new LinkedList();
        }

        /**
         * This function adds the personBeingFollowed to the list of people the follower is follwing. It does this by going
         * to the twitterScrapeResults adjacency matrix of the follower and adds the integer representation of
         * personBeingFollowed to that matrix.
         * @param follower
         * @param personBeingFollowed
         */
        public void addFollower(int follower, int personBeingFollowed) {
            twitterScrapeResults[follower].add(personBeingFollowed);
        }

        public void addFollowerReverse(int follower, int personBeingFollowed) {
            twitterScrapeResults[personBeingFollowed].add(follower);
        }

        // A function used by DFS
        public void depthFirstSearchHelper(int v, boolean visited[]) {
            // Mark the current node as visited and print it
            visited[v] = true;

            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = twitterScrapeResults[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n])
                    depthFirstSearchHelper(n, visited);
            }
        }

        /**
         * This returns the K people required to
         */
        public  List<Integer> findKPeople() {
            solution reverse = new solution(numberOfPeople);
            List<Integer> answer = new ArrayList<Integer>();

            // Generates the reverse map
            for (int i=0; i<numberOfPeople; i++) {
                for (int j=0; j<twitterScrapeResults[i].size(); j++ ) {
                    reverse.addFollowerReverse(i, twitterScrapeResults[i].get(j));
                }
            }

            boolean visited[] = new boolean[numberOfPeople];
            LinkedList<Integer> points[];
            int v = -1; // candidate for Influencer

            for (int i = 0; i < numberOfPeople; i++) { visited[i] = false; } // sets the visited group to False

            // Performs a depth first search
            // The last candidate to be visited is the Influencer so I store its position as v
            for (int i = 0; i < numberOfPeople; i++) {
                if (visited[i] == false) {
                    depthFirstSearchHelper(i, visited);
                    v = i;
                }
            }

            for (int i = 0; i < numberOfPeople; i++) { visited[i] = false; } // Resets the visited group to False

            depthFirstSearchHelper(v, visited);
            for(int i = 0; i < numberOfPeople; i++) {
                if(visited[i] == false) {
                    answer.add(-1);
                    return answer;
                }
            }

            for (int i = 0; i < numberOfPeople; i++) { visited[i] = false; } // Resets the visited group to False
            reverse.depthFirstSearchHelper(v, visited);
            for (int i=0; i<numberOfPeople; i++) {
                if (visited[i]) { answer.add(i); }
            }

            return answer;
        }
    }

    /**
     * From the internet: this is known as a Fisher-Yates shuffle
     * @param ar
     */
    private static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void testBasicFindKPeople() {
        twitterMap example = new twitterMap(7);

        example.addFollower(0, 1);
        example.addFollower(0, 2);
        example.addFollower(1, 3);
        example.addFollower(4, 1);
        example.addFollower(6, 4);
        example.addFollower(4, 6);

        example.addFollower(6, 0);
        example.addFollower(5, 2);
        example.addFollower(5, 6);
        example.addFollower(6, 5);

        List<Integer> ans = example.findKPeople();

        List<Integer> ansReal = new ArrayList<Integer>();
        ansReal.add(4);
        ansReal.add(5);
        ansReal.add(6);

        assertEquals(ansReal, ans);
    }

    public void testAdvanced(int numberOfNodes, int numberOfEdges, int numberOfMotherVertices) {
        Random rnd = ThreadLocalRandom.current();
        int counter = 0;

        solution sol = new solution(numberOfNodes);
        twitterMap example = new twitterMap(numberOfNodes);
        int[] randomness = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) { randomness[i] = i; }

        shuffleArray(randomness);

        for (int v = 0; v < numberOfNodes-1; v++) {
            int x = ((ThreadLocalRandom) rnd).nextInt(v+1, numberOfNodes);
            sol.addFollower(x, v);
            example.addFollower(x, v);
            counter += 1;
        }

        while (counter < numberOfNodes) {
            int x = ((ThreadLocalRandom) rnd).nextInt(numberOfNodes);
            int y = ((ThreadLocalRandom) rnd).nextInt(numberOfNodes);
            example.addFollower(x, y);
            sol.addFollower(x, y);
            counter += 1;

        }

        List<Integer> solAnsEarly = sol.findKPeople();
        List<Integer> ansEarly = example.findKPeople();
        assertEquals(solAnsEarly, ansEarly);

        int firstMother = solAnsEarly.get(0);
        // add more mother vertices
        for (int i=0; i< (numberOfMotherVertices - 1); i++) {
            int newMother = rnd.nextInt(numberOfNodes);
            example.addFollower(newMother, firstMother);
            sol.addFollower(newMother, firstMother);
        }

        List<Integer> solAns = sol.findKPeople();
        List<Integer> ans = example.findKPeople();
        assertEquals(solAns, ans);
    }

    @Test(timeout=9000)
    public void callTests() {

        testBasicFindKPeople();
        testAdvanced(100, 4949, 7);
        testAdvanced(250, 31000, 120);
        testAdvanced(500, 124000, 321);
        testAdvanced(1000, 499000, 72);
        testAdvanced(10000, 49994999, 972);
        testAdvanced(1000000, 499999500, 20000);


    }

}