import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {

    BalancedBST tree;
    int[] massiveTree;
    @BeforeEach
    void setUp() {
        tree = new BalancedBST();
        massiveTree = new int[] {1, 2, 3, 5, 6, 9, 13, 11, 12};
    }

    @Test
    void testCreateTree () {
        tree.GenerateTree(massiveTree);
        assertNotNull(tree.Root);
        assertEquals(6, tree.Root.NodeKey);
        assertEquals(0, tree.Root.Level);
        assertEquals(1, tree.Root.LeftChild.Level);
        assertEquals(1, tree.Root.RightChild.Level);
        assertEquals(2, tree.Root.RightChild.RightChild.Level);
    }
}