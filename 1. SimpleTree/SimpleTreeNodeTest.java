import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeNodeTest {
    SimpleTreeNode<Integer> root;
    SimpleTree<Integer> tree;

    @BeforeEach
    void setUp() {
        root = new SimpleTreeNode<>(0, null);
        tree = new SimpleTree<>(root);
    }

    @Test
    void countAndDeleteTest() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(101, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(103, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(104, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(105, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(106, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        tree.AddChild(node2, node6);
        tree.AddChild(node1, node3);
        assertEquals(5, tree.Count());
        tree.DeleteNode(node3);
        assertEquals(4, tree.Count());
        tree.AddChild(node1, node4);


        assertEquals(5, tree.Count());
        tree.DeleteNode(node1);
        assertEquals(3, tree.Count());
    }

    @Test
    void leafCountWIthDeleteTest() {
        assertEquals(1, tree.LeafCount());

        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(101, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(103, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(104, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(105, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(106, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        assertEquals(2, tree.LeafCount());

        tree.AddChild(node1, node3);
        tree.AddChild(node2, node4);
        assertEquals(2, tree.LeafCount());
        tree.AddChild(root, node5);
        tree.AddChild(root, node6);
        assertEquals(4, tree.LeafCount());
        tree.DeleteNode(node3);
        assertEquals(4, tree.LeafCount());
        tree.DeleteNode(node6);
        assertEquals(3, tree.LeafCount());
    }

    @Test
    void addChildTest() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(101, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(103, null);

        tree.AddChild(root, node1);
        tree.AddChild(node1, node2);
        tree.AddChild(node2, node3);

        assertTrue(root.Children.contains(node1));
        assertEquals(root, node1.Parent);
        assertEquals(node1, node2.Parent);
        assertTrue(node2.Children.contains(node3));
        assertNull(node3.Children);
    }

    @Test
    void deleteNodeTest() {
        List<SimpleTreeNode<Integer>> nodes = new ArrayList<>();
        nodes.add(root);

        for (int i = 1; i < 10; i++) {
            SimpleTreeNode<Integer> node = new SimpleTreeNode<>(i, null);
            nodes.add(node);
            tree.AddChild(root, node);
        }

        assertEquals(10, tree.Count());
        assertTrue(root.Children.contains(nodes.get(5)));
        tree.DeleteNode(nodes.get(5));
        assertFalse(root.Children.contains(nodes.get(5)));

        for (int i = 1; i < nodes.size(); i++) {
            tree.DeleteNode(nodes.get(i));
        }

        assertEquals(1, tree.Count());
    }

    @Test
    void deleteRootTest() {
        assertNotNull(tree.Root);
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        tree.AddChild(node2, node3);
        tree.DeleteNode(root);
        assertNull(tree.Root);
    }

    @Test
    void GetAllNodesTest() {
        assertEquals(1, tree.GetAllNodes().size());
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);

        tree.AddChild(root, node1);
        tree.AddChild(node1, node2);
        tree.AddChild(node1, node3);
        assertEquals(4, tree.GetAllNodes().size());
        tree.DeleteNode(node3);
        assertEquals(3, tree.GetAllNodes().size());
    }

    @Test
    void findNodesByValueTest() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        tree.AddChild(root, node3);
        tree.AddChild(root, node4);
        tree.AddChild(root, node5);
        tree.AddChild(root, node6);

        assertTrue(tree.FindNodesByValue(2).contains(node2));
        assertEquals(2, tree.FindNodesByValue(2).size());
        assertEquals(0, tree.FindNodesByValue(10).size());

        List<SimpleTreeNode<Integer>> nodesByValue = tree.FindNodesByValue(2);
        assertTrue(nodesByValue.contains(node2));
    }

    @Test
    void MoveNodeTest() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(101, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(103, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(104, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(105, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(106, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        tree.AddChild(root, node3);
        tree.AddChild(root, node4);
        tree.AddChild(root, node5);
        tree.AddChild(root, node6);
        assertEquals(6, root.Children.size());

        tree.MoveNode(node4, node6);
        tree.MoveNode(node5, node6);
        assertTrue(node6.Children.contains(node4));
        assertSame(node4.Parent, node6);
        assertSame(node5.Parent, node6);
        assertEquals(4, root.Children.size());
        assertEquals(2, node6.Children.size());
    }

    @Test
    void ChangeChild() {
        SimpleTreeNode<Integer> node1 = new SimpleTreeNode<>(101, null);
        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(103, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(104, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(105, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(106, null);

        tree.AddChild(root, node1);
        tree.AddChild(root, node2);
        tree.AddChild(node1, node3);
        tree.AddChild(node3, node4);
        assertEquals(5, tree.Count());
        assertTrue(node3.Children.contains(node4));
        assertSame(node4.Parent, node3);
        tree.DeleteNode(node4);
        assertTrue(node3.Children.isEmpty());
        assertEquals(4, tree.Count());
        tree.AddChild(node3, node6);
        assertEquals(5, tree.Count());
        assertTrue(node3.Children.contains(node6));
        assertSame(node6.Parent, node3);
    }

    

}