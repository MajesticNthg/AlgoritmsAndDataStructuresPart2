import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {
    int[] a;

    @BeforeEach
    void setUp () {
        a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
    }

    @Test
    void testOne () {
        int[] BalancedBST = AlgorithmsDataStructures2.GenerateBBSTArray(a);
        assertEquals(BalancedBST.length, a.length);
    }
}