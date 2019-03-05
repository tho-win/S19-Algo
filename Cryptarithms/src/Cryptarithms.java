import java.util.*;

public class Cryptarithms {

    static Map<Character, Integer> result;
    static int sumLength;
    /*
    static List<Integer> availableDigit = new ArrayList<Integer>(){{
        for (int digit = 0; digit < 10; digit++) add(digit);
    }};
    */
    /**
     * @param equation a list of strings that make up the equation, last element is the sum, all other elements are addends
     * @return a map from characters to integers representing your final solution
     */
    public static Map<Character, Integer> solve(List<String> equation) {
        // TODO: implement this method
        result = new HashMap<>();
        List<StringBuilder> modEquation = new ArrayList<>();
        sumLength= equation.get(equation.size()-1).length();

        for (String s: equation){
            //collect all characters and add to map
            StringBuilder rev = new StringBuilder();
            for (int i = s.length()-1; i > -1; i--){
                rev.append(s.charAt(i));
            }
            modEquation.add(rev);
        }
        solve(modEquation, 0, 0, 0);
        if (result.isEmpty()) return null;
        return result;
    }

    private static boolean solve(List<StringBuilder> equation, int i, int j, int carry) {
        if (j >= sumLength) { //base case: if beyond leftmost digit
            if (carry == 0) return true;
            return false;
        }
        Character c = equation.get(i).charAt(j); //Character to be assigned
        if (i < equation.size() - 1) { //if assign characters in addends
            if (!result.containsKey(c)) { //if character is not assigned
                for (int digit = 0; digit < 10; digit++) {
                    while (result.containsValue(digit)) digit++; //avoid assigning taken digits
                    if (digit >= 10) break;
                    result.put(c, digit);
                    if (solve(equation, nextI(equation, i+1, j), j, carry)) return true;
                    else{
                        result.remove(c);
                    }
                }
                return false;
            }
            else if (result.containsKey(c)) { //if character is assigned
                return solve(equation, nextI(equation, i+1, j), j, carry);
            }
        }
        else if (i == equation.size() - 1){ //if characters in sum
            int sum = sumSoFar(equation, j);
            if (!result.containsKey(c)){ //if character is not assigned
                if (result.containsValue((sum+carry)%10)) return false; // if sum is assigned to other character
                else { //if sum is not assigned to other character
                    result.put(c, (sum+carry)%10);
                    if (solve(equation, nextI(equation, 0, j+1), j+1, (sum+carry)/10)) return true;
                    else {
                        result.remove(c);
                        return false;
                    }
                }
            }
            else if (result.containsKey(c)) { //if character is assigned
                if (result.get(c) == (sum+carry)%10){
                    if (solve(equation, nextI(equation, 0, j+1), j+1, (sum+carry)/10)) return true;
                    return false;
                }
                else if (result.get(c) != (sum+carry)%10){
                    return false;
                }
            }
        }
        return false;
    }

    private static int nextI(List<StringBuilder> equation, int i, int j){
        while (i < equation.size()-1 && j >= equation.get(i).length()) {
            i++;
        }
        return i;
    }

    private static int sumSoFar(List<StringBuilder>equation, int j){
        int sum = 0;
        int i  = 0;
        while (i < equation.size()-1){
            if (j >= equation.get(i).length()) i = nextI(equation, i+1, j);
            if (i >= equation.size()-1) break;
            sum += result.get(equation.get(i).charAt(j));
            i = nextI(equation, i+1, j);
        }
        return sum;
    }
    /**
     * Add for compilation and incremental testing purposes
     * @param args
     */
    public static void main(String args[]) {
        //System.out.println("Welcome to Cryptarithms!");
        List<String> input = new ArrayList<>();
        input.add("A");
        input.add("B");
        //input.add("A");
        input.add("C");
        System.out.println(Cryptarithms.solve(input));
    }
}
