import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {

    aBST tree;

    @BeforeEach
    void setUp () {
        tree = new aBST(4);
    }

    @Test
    void testAddKey() {
        tree.AddKey(4);
        tree.AddKey(3);
        tree.AddKey(5);
        tree.AddKey(6);

        ArrayList<Integer> testWideAllNodes = new ArrayList<>();
        testWideAllNodes = tree.WideAllNodes();
        assertEquals(4, testWideAllNodes.size());
    }

    @Test
    void testWideAllNodes() {
        tree.AddKey(10);
        tree.AddKey(5);
        tree.AddKey(3);
        tree.AddKey(8);
        tree.AddKey(15);
        tree.AddKey(13);
        tree.AddKey(18);

        ArrayList<Integer> testWideAllNodes = new ArrayList<>();
        testWideAllNodes = tree.WideAllNodes();
        assertEquals(7, testWideAllNodes.size());
        assertEquals(testWideAllNodes.getFirst(), 10);
        assertEquals(testWideAllNodes.getLast(), 18);
        assertEquals(testWideAllNodes.get(1), 5);
        assertEquals(testWideAllNodes.get(2), 15);
        assertEquals(testWideAllNodes.get(3), 3);
    }

    @Test
    void testFindKeyIndex() {
        tree.AddKey(10);
        tree.AddKey(5);
        tree.AddKey(3);
        tree.AddKey(8);
        tree.AddKey(15);
        tree.AddKey(13);


        assertEquals(tree.FindKeyIndex(10), 0);
        assertNull(tree.FindKeyIndex(11));
        assertEquals(tree.FindKeyIndex(18), -6);
    }

    @Test
    void testEmptyTree() {
        aBST testTree = tree = new aBST(0);
        assertNull(tree.FindKeyIndex(11));
    }

    @Test
    void testNotFindIndex() {
        tree.AddKey(10);
        tree.AddKey(5);
        tree.AddKey(3);
        tree.AddKey(8);
        tree.AddKey(15);
        tree.AddKey(13);

        assertNull(tree.FindKeyIndex(11));
    }

}
