
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;

public class TestRunner {

    @Test
    public void exampleTest() {
        Student[] students = new Student[10];
        for (int i = 0; i < 10; i++) {
            students[i] = new Student(i+1 ,Integer.toString(i));
        }
        assertEquals(NthPlaceLoser.NthPlaceLoser(students, 1 ).rank,  9);
        assertEquals(NthPlaceLoser.NthPlaceLoser(students, 3 ).rank,  7);
    }


}
