
public class NthPlaceLoser {


    public static Student NthPlaceLoser(Student[] aList, int N) {
        // TODO implement this yourself
<<<<<<< HEAD
        N = aList.length - N - 1;
        int lo = 0, hi = aList.length-1;
        while (hi > lo) {
            int j = partition(aList, lo, hi);
            if (N < j) hi = j - 1;
            else if (N > j) lo = j + 1;
            else return aList[N];
        }
        return aList[N];
=======
        return aList[0];
>>>>>>> 5c98dfa9e13514522b3d12946cab2338fa10a7e3
    }

    private static int partition(Student[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        while (true){
            while(less(a[++i], a[lo]))
                if (i == hi) break;
                while(less(a[lo], a[--j]))
                    if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);

        }
        exch(a, lo, j);
        return j;
    }
    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    /**
     * This method returns whether a student is less than another student in respect
     * to how your compareTo method is defined
     * @param v First Student
     * @param w Second Student
     * @return if v (first student) is less than w (second student)
     */
    private static boolean less(Student v, Student w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    /**
     * This method exchanges a[i] and a[j] in an array
     * @param a array in which you want to swap
     * @param i index of first item
     * @param j index of second item
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

<<<<<<< HEAD

=======
>>>>>>> 5c98dfa9e13514522b3d12946cab2338fa10a7e3
    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String[] args) {

    }

}