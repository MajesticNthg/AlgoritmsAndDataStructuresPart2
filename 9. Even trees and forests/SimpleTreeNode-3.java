import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleTreeTest {

    @Test
    public void testEvenTrees_EmptyTree() {
        SimpleTree<Integer> tree = new SimpleTree<>(null);
        ArrayList<Integer> result = tree.EvenTrees();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEvenTrees_SingleNode() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        ArrayList<Integer> result = tree.EvenTrees();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEvenTrees_TwoNodes() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(2, root);
        root.Children = Arrays.asList(child);

        SimpleTree<Integer> tree = new SimpleTree<>(root);
        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testEvenTrees_ThreeNodesLine() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> child1 = new SimpleTreeNode<>(2, root);
        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(3, child1);
        root.Children = Arrays.asList(child1);
        child1.Children = Arrays.asList(child2);

        SimpleTree<Integer> tree = new SimpleTree<>(root);
        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testEvenTrees_FourNodes() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> child1 = new SimpleTreeNode<>(2, root);
        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(3, root);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(4, child1);

        root.Children = Arrays.asList(child1, child2);
        child1.Children = Arrays.asList(child3);

        SimpleTree<Integer> tree = new SimpleTree<>(root);
        ArrayList<Integer> result = tree.EvenTrees();

        assertEquals(Arrays.asList(1, 2), result);
    }


    // Тест 7: Дерево где все поддеревья нечетного размера
    @Test
    public void testEvenTrees_AllOddSubtrees() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);

        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, root);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, root);

        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, child2);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child2);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child3);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child3);

        root.Children = Arrays.asList(child2, child3);
        child2.Children = Arrays.asList(child4, child5);
        child3.Children = Arrays.asList(child6, child7);

        SimpleTree<Integer> tree = new SimpleTree<>(root);
        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }
    @Test
    public void testEvenTrees_StringValues() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("A", null);
        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("B", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("C", root);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("D", child1);

        root.Children = Arrays.asList(child1, child2);
        child1.Children = Arrays.asList(child3);

        SimpleTree<String> tree = new SimpleTree<>(root);
        ArrayList<String> result = tree.EvenTrees();

        assertEquals(Arrays.asList("A", "B"), result);
    }

    private boolean containsPair(ArrayList<Integer> result, int parent, int child) {
        for (int i = 0; i < result.size() - 1; i += 2) {
            if (result.get(i) == parent && result.get(i + 1) == child) {
                return true;
            }
            if (result.get(i) == child && result.get(i + 1) == parent) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testEmptyTree() {
        SimpleTree<Integer> tree = new SimpleTree<>(null);

        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleNodeTree() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testTwoNodeTree() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(2, root);
        tree.AddChild(root, child);

        ArrayList<Integer> result = tree.EvenTrees();

        assertTrue(result.isEmpty());
    }

}
