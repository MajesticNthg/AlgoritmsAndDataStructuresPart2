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

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) {
            return true;
        }

        boolean isBalanced = true;
        isBalanced = checkBalanced(root_node);


        if (!isBalanced) {
            return false;
        } else {
            return IsBalanced(root_node.RightChild) && IsBalanced(root_node.LeftChild);
        }
    }

    private boolean checkBalanced (BSTNode node) {
        int sizeRightTree = 0;
        int sizeLeftTree = 0;

        if (node.RightChild != null) {
            sizeRightTree = rightTree(node.RightChild, sizeRightTree);
        }
        if (node.LeftChild != null) {
            sizeLeftTree = leftTree(node.LeftChild, sizeLeftTree);
        }

        return sizeRightTree - sizeLeftTree <= 1 && sizeLeftTree - sizeRightTree <= 1;
    }
    private int rightTree (BSTNode node, int size) {
        if (node.RightChild != null) {
            size++;
        } else {
            return size;
        }
        return rightTree(node.RightChild, size);
    }

    private int leftTree (BSTNode node, int size) {
        if (node.LeftChild != null) {
            size++;
        } else {
            return size;
        }
        return leftTree(node.LeftChild, size);
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
        if (root_node == null) {
            return true;
        }

        boolean isBalanced = true;
        isBalanced = checkBalanced(root_node);


        if (!isBalanced) {
            return false;
        } else {
            return IsBalanced(root_node.RightChild) && IsBalanced(root_node.LeftChild);
        }
    }

    private boolean checkBalanced (BSTNode node) {
        int sizeRightTree = 0;
        int sizeLeftTree = 0;

        if (node.RightChild != null) {
            sizeRightTree = rightTree(node, sizeRightTree);
        }
        if (node.LeftChild != null) {
            sizeLeftTree = leftTree(node, sizeLeftTree);
        }

        return sizeRightTree - sizeLeftTree <= 1 && sizeLeftTree - sizeRightTree <= 1;
    }
    private int rightTree (BSTNode node, int size) {
        if (node.RightChild != null) {
            size++;
        } else {
            return size;
        }
        return rightTree(node.RightChild, size);
    }

    private int leftTree (BSTNode node, int size) {
        if (node.LeftChild != null) {
            size++;
        } else {
            return size;
        }
        return leftTree(node.LeftChild, size);
    }
}
