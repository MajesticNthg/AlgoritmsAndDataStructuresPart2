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

    @Test
    void isBalancedFirst() {
        tree.GenerateTree(massiveTree);
        assertTrue(tree.IsBalanced(tree.Root));
    }

    @Test
    void isBalancedSecond() {
        BSTNode root = new BSTNode(10, null);
        root.LeftChild = new BSTNode(5, root);
        root.RightChild = new BSTNode(15, root);

        root.LeftChild.LeftChild = new BSTNode(2, root.LeftChild);
        root.LeftChild.RightChild = new BSTNode(8, root.LeftChild);

        root.LeftChild.LeftChild.LeftChild = new BSTNode(1, root.LeftChild.LeftChild);
        root.LeftChild.LeftChild.RightChild = new BSTNode(3, root.LeftChild.LeftChild);

        assertFalse(tree.IsBalanced(root));
    }

    @Test
    void isBalancedThird() {
        BSTNode root = new BSTNode(10, null);
        root.LeftChild = new BSTNode(5, root);
        root.RightChild = new BSTNode(15, root);

        root.LeftChild.LeftChild = new BSTNode(2, root.LeftChild);
        root.LeftChild.RightChild = new BSTNode(8, root.LeftChild);

        root.LeftChild.LeftChild.LeftChild = new BSTNode(1, root.LeftChild.LeftChild);

        assertFalse(tree.IsBalanced(root));
    }

    @Test
    void isBalancedFourth() {
        BSTNode root = new BSTNode(10, null);
        root.LeftChild = new BSTNode(5, null);
        root.LeftChild.LeftChild = new BSTNode(2, null);
        root.LeftChild.LeftChild.LeftChild = new BSTNode(1, null);
        root.RightChild = new BSTNode(15, null);
        root.RightChild.RightChild = new BSTNode(20, null);

        assertFalse(tree.IsBalanced(root));
    }

    @Test
    void isBalancedFifth() {
        BSTNode root2 = new BSTNode(10, null);
        root2.LeftChild = new BSTNode(5, null);
        root2.LeftChild.LeftChild = new BSTNode(2, null);

        assertFalse(tree.IsBalanced(root2));
    }
}
