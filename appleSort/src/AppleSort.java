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
        int totalApples = 0;
        for (int i = 0; i < apples.size(); i++){
            totalApples+= apples.get(i).length;
        }
        Comparable[] lastList = new Comparable[totalApples];
        Apple[] alist = new Apple[totalApples];
        int index = 0;
        for (int i = 0; i < apples.size(); i++){
            for (int j = 0; j < apples.get(i).length; j++){
                alist[index++] = apples.get(i)[j];
            }
        }
        sort(lastList, alist, 0, totalApples-1);
        return lastList;
    }

    //lo, hi , mid is index within apples, each index is an array
    private static void sort(Comparable[] lastList, Apple[] alist, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(lastList, alist, lo, mid);
        sort(lastList, alist, mid+1, hi);
        merge(lastList, alist, lo, mid, hi);

    }

    private static void merge(Comparable[] lastList, Apple[] alist, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        if (less(alist[mid], alist[mid+1])) return;
        for (int k = lo; k <= hi; k++){
            if (i > mid) lastList[k] = alist[j++];
            else if (j > hi) lastList[k] = alist[i++];
            else if (less(alist[j], alist[i])) lastList[k] = alist[j++];
            else lastList[k] = alist[i++];
        }

    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

    }

}

