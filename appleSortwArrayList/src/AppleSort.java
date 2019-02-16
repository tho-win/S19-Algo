import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class AppleSort {


    /**
     * Implement this method that takes in an arraylist of Apples and sorts them into one giant array of Apples.
     * You are free to implement any helper methods you need.
     * @param apples
     * @return
     */
    public static Comparable[] sort(ArrayList<Apple[]> apples){
        Comparable[] lastList;
        ArrayList<ArrayList<Apple>> appleAL = new ArrayList<>();

        int numBucket = apples.size();
        int totalApple = 0;
        for (int i = 0; i < numBucket; i++){
            totalApple += apples.get(i).length;
            appleAL.add(new ArrayList<Apple>(Arrays.asList(apples.get(i))));
        }

        lastList = new Comparable[totalApple];
        if (totalApple == 0) return lastList;

        Comparable min = new Apple(100, 100);
        ArrayList<Apple> minAL = new ArrayList<>();
        for (int i = 0; i < totalApple; i++){
            for (int j = 0; j < appleAL.size(); j++){
                if (appleAL.get(j).size() != 0){
                    Comparable temp = appleAL.get(j).get(0);
                    if (less(temp, min)){
                        min = temp;
                        minAL = appleAL.get(j);
                    }
                }
            }
            lastList[i] = min;

            minAL.remove(0);
            if (minAL.size() == 0) {
                appleAL.remove(minAL);
            }
            min = new Apple(100, 100);
        }
        return lastList;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

    }

}

