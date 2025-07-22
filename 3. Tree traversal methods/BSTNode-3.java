import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    BSTNode <String> Root;
    BSTFind <String> BSTFind;
    BST<String> myTree;

    @BeforeEach
    void setUp() {
        Root = new BSTNode<>(2, "apple", null);
        myTree = new BST<>(Root);
    }

    @Test
    void FirstTierSearchNonExistentKey () {
        assertFalse(myTree.FindNodeByKey(22).NodeHasKey);
        myTree.AddKeyValue(22, "orange");
        assertTrue(myTree.FindNodeByKey(22).NodeHasKey);
    }

    @Test
    void FirstTierSearchLeftAndRightChild () {
        assertFalse(myTree.FindNodeByKey(1).NodeHasKey);
        assertFalse(myTree.FindNodeByKey(3).NodeHasKey);
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(3, "strawberry");
        assertTrue(myTree.FindNodeByKey(1).NodeHasKey);
        assertTrue(myTree.FindNodeByKey(3).NodeHasKey);
        assertEquals(myTree.Root.RightChild.NodeKey, myTree.FindNodeByKey(3).Node.NodeKey);
        assertEquals(myTree.Root.LeftChild.NodeKey, myTree.FindNodeByKey(1).Node.NodeKey);
        assertEquals(3, myTree.Count());
        myTree.AddKeyValue(3, "blackberry");
        assertEquals(3, myTree.Count());
    }

    @Test
    void FirstTierSearchMinAndMax () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");

        assertEquals(33, myTree.FinMinMax(Root, true).NodeKey);
        assertEquals(8, myTree.FinMinMax(myTree.FindNodeByKey(10).Node, false).NodeKey);
    }

    @Test
    void FirstTierDeleteNodeWithKey () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");

        assertEquals(myTree.FindNodeByKey(10).Node.RightChild.NodeKey, 20);
        myTree.DeleteNodeByKey(20);
        assertNotEquals(myTree.FindNodeByKey(10).Node.RightChild.NodeKey, 20);
    }

    @Test
    void countNodeAfterDeleteTwoChildTree () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");
        myTree.DeleteNodeByKey(10);

        assertEquals(6, myTree.Count());
    }
    @Test
    void countNodeAfterDelete() {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(3, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(41, "mango");
        myTree.AddKeyValue(18, "lime");
        myTree.AddKeyValue(14, "blackberry");
        myTree.AddKeyValue(5, "watermelon");

        assertEquals(10, myTree.Count());
        myTree.DeleteNodeByKey(2);
        myTree.DeleteNodeByKey(1);
        myTree.DeleteNodeByKey(41);
        assertEquals(7, myTree.Count());
        myTree.DeleteNodeByKey(132);
        assertEquals(7, myTree.Count());
        myTree.AddKeyValue(1, "orange");
        assertEquals(8, myTree.Count());

    }
    @Test
    void AddKeyValueTest () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(3, "banana");

        assertEquals(5, myTree.Count());
    }

    @Test
    void FindNodeByKeyTest () {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(1, "strawberry");

        assertEquals(myTree.FindNodeByKey(22).Node.NodeValue, "peanut");
    }

    @Test
    void FindMinMaxTest () {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(1, "strawberry");

        assertEquals(myTree.FinMinMax(myTree.Root, true).NodeKey, 22);
    }

    @Test
    void CountTest() {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(1, "strawberry");

        assertEquals(4, myTree.Count());
    }

    @Test
    void DeleteLeaf () {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(1, "strawberry");

        myTree.DeleteNodeByKey(22);
        assertEquals(myTree.FinMinMax(myTree.Root, true).NodeKey, 10);
        assertEquals(myTree.FinMinMax(myTree.Root, false).NodeKey, 1);
    }

    @Test
    void DeleteRootWIthTwoChild() {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(1, "strawberry");

        myTree.DeleteNodeByKey(2);
        assertEquals(myTree.Root.NodeKey, 10);
    }

    @Test
    void deleteRootWithOneRightChild() {
        myTree.AddKeyValue(10, "butter");
        myTree.DeleteNodeByKey(2);

        assertEquals(myTree.Root.NodeKey, 10);
    }

    @Test
    void deleteRootWithOneLeftChild() {
        myTree.AddKeyValue(1, "strawberry");
        myTree.DeleteNodeByKey(2);

        assertEquals(myTree.Root.NodeKey, 1);
    }

    @Test
    void deleteWithMiddleNode() {
        myTree.AddKeyValue(10, "butter");
        myTree.AddKeyValue(22, "peanut");
        myTree.AddKeyValue(9, "strawberry");

        myTree.DeleteNodeByKey(9);
        assertEquals(3, myTree.Count());
    }

    @Test
    void addNodeInWideAllNodes () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");

        ArrayList<BSTNode> test = myTree.WideAllNodes();
        assertEquals(7, test.size());
        assertEquals(test.getFirst().NodeValue, this.Root.NodeValue);
        assertEquals(test.get(1), myTree.FindNodeByKey(1).Node);
        assertEquals(test.get(2), myTree.FindNodeByKey(10).Node);
        assertEquals(test.getLast().NodeValue, myTree.FindNodeByKey(33).Node.NodeValue);

    }

        @Test
    void addNodeInWideAllNodesForSecondTree () {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(8, "pear");
        myTree.AddKeyValue(7, "banana");
        myTree.AddKeyValue(9, "strawberry");
        myTree.AddKeyValue(6, "blackberry");
        myTree.AddKeyValue(5, "raspberry");

        ArrayList<BSTNode> test = myTree.WideAllNodes();
        assertEquals(test.size(), 8);
        assertEquals(test.getLast().NodeKey, 5);
        assertEquals(test.get(6).NodeKey, 6);
        assertEquals(test.get(5).NodeKey, 9);

    }

    @Test
    void addNodeInWideAllNodesForThirdTree () {
        myTree.AddKeyValue(5, "orange");
        myTree.AddKeyValue(7, "juice");
        myTree.AddKeyValue(2, "pear");
        myTree.AddKeyValue(0, "strawberry");
        myTree.AddKeyValue(1, "blackberry");
        myTree.AddKeyValue(3, "raspberry");
        myTree.AddKeyValue(15, "cherry");

        ArrayList<BSTNode> test = myTree.WideAllNodes();
        assertEquals(test.getLast().NodeKey, 15);
        assertEquals(test.get(5).NodeKey, 7);
        assertEquals(test.get(4).NodeKey, 3);
        assertEquals(test.get(3).NodeKey, 1);
    }

    @Test
    void DeepAllNodesTestPreOrder() {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");

        ArrayList<BSTNode> test = myTree.DeepAllNodes(2);
        assertEquals(test.get(0), this.Root);
        assertEquals(test.getLast().NodeValue, myTree.FindNodeByKey(33).Node.NodeValue);
    }

    @Test
    void DeepAllNodesTestInOrder() {
        myTree.AddKeyValue(1, "orange");
        myTree.AddKeyValue(0, "juice");
        myTree.AddKeyValue(10, "pear");
        myTree.AddKeyValue(20, "banana");
        myTree.AddKeyValue(33, "strawberry");
        myTree.AddKeyValue(8, "blackberry");

        ArrayList<BSTNode> test = myTree.DeepAllNodes(0);
        assertEquals(test.getFirst().NodeValue, myTree.FindNodeByKey(0).Node.NodeValue);
        assertEquals(test.getLast().NodeValue, myTree.FindNodeByKey(33).Node.NodeValue);
        assertEquals(test.get(2).NodeValue, this.Root.NodeValue);
    }
}
