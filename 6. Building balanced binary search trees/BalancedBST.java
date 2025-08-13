import java.util.*;

class BSTNode {
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int Level;

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        int[] sort = Arrays.copyOf(a, a.length);
        Arrays.sort(sort);

        this.Root = createBalancedTree(this.Root, 0, sort.length - 1, sort);
    }

    private BSTNode createBalancedTree(BSTNode parent, int startIndex, int endIndex, int[] sort) {
        if (endIndex < startIndex) {
            return null;
        }

        int midIndex = (endIndex + startIndex) / 2;
        BSTNode node = new BSTNode(sort[midIndex], parent);

        if (node.Parent == null) {
            node.Level = 0;
        } else {
            node.Level = node.Parent.Level + 1;
        }

        node.LeftChild = createBalancedTree(node, startIndex, midIndex - 1, sort);
        node.RightChild = createBalancedTree(node, midIndex + 1, endIndex, sort);

        return node;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node.RightChild == null && root_node.LeftChild == null) {
            return true;
        }

        boolean isBalanced = false;
        if (root_node.LeftChild == null) {
            isBalanced = checkBalanced(root_node.RightChild, 0);
        } else if (root_node.RightChild == null) {
            isBalanced = checkBalanced(root_node.LeftChild, 0);
        }

        if (!isBalanced) {
            return false;
        }

        IsBalanced(root_node.LeftChild);
        IsBalanced(root_node.RightChild);

        return true;
    }

    private boolean checkBalanced (BSTNode node, int depth) {
        if (node.LeftChild == null && node.RightChild == null) {
            return true;
        }

        if (depth > 0) {
            return false;
        }

        if (node.LeftChild != null) {
            checkBalanced(node.LeftChild, 1);
        }

        if (node.RightChild != null) {
            checkBalanced(node.RightChild, 1);
        }

        return false;
    }
}