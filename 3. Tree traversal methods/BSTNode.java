import java.io.*;
import java.util.*;

class BSTNode<T> {
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() { Node = null; }
}

class BST<T> {
    BSTNode<T> Root;

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey (int key) {
        BSTFind<T> targetNode = new BSTFind<>();

        if (this.Root == null) {
            return targetNode;
        }

        return (SearchNodeByKey(this.Root, targetNode, key));
    }

    private BSTFind<T> SearchNodeByKey (BSTNode<T> ThisNode, BSTFind<T> targetNode, int key) {
        if (ThisNode.NodeKey > key && ThisNode.LeftChild != null) {
            return SearchNodeByKey(ThisNode.LeftChild, targetNode, key);
        }

        if (ThisNode.NodeKey < key && ThisNode.RightChild != null) {
            return SearchNodeByKey(ThisNode.RightChild, targetNode, key);
        }

        targetNode.Node = ThisNode;

        if (ThisNode.NodeKey == key) {
            targetNode.NodeHasKey = true;
            return targetNode;
        }

        targetNode.ToLeft = false;
        targetNode.NodeHasKey = false;

        if (key < ThisNode.NodeKey) {
            targetNode.ToLeft = true;
        }

        return targetNode;
    }

    public boolean AddKeyValue (int key, T val) {
        BSTFind<T> SearchKey = FindNodeByKey(key);
        BSTNode<T> targetNode = new BSTNode<>(key, val, null);

        if (SearchKey.Node == null) {
            this.Root = targetNode;
            return true;
        }

        if (SearchKey.NodeHasKey) {
            return false;
        }

        BSTNode<T> Parent = SearchKey.Node;
        BSTNode<T> newNode = new BSTNode<>(key, val, Parent);
        if (SearchKey.ToLeft) {
            SearchKey.Node.LeftChild = newNode;
        } else {
            SearchKey.Node.RightChild = newNode;
        }

        return true;
    }

    public BSTNode<T> FinMinMax (BSTNode<T> FromNode, boolean FindMax) {
        if (FindMax) {
            return SearchFindMax(FromNode);
        }

        return SearchFindMin(FromNode);
    }

    private BSTNode<T> SearchFindMax (BSTNode<T> node) {
        if (node.RightChild != null) {
            return SearchFindMax(node.RightChild);
        }

        return node;
    }

    private BSTNode<T> SearchFindMin (BSTNode<T> node) {
        if (node.LeftChild != null) {
            return SearchFindMin(node.LeftChild);
        }

        return node;
    }

    public boolean DeleteNodeByKey (int key) {
        BSTFind<T> SearchKey = FindNodeByKey(key);

        if (SearchKey.Node == null || !SearchKey.NodeHasKey) {
            return false;
        }

        if (deleteLeafs(SearchKey.Node)) return true;
        if (deleteRootWithoutChild(SearchKey.Node)) return true;
        if (deleteRootWithOneChild(SearchKey.Node)) return true;
        if (deleteWithTwoChild(SearchKey.Node)) return true;
        return deleteInsideTreeWithOneChild(SearchKey.Node);
    }

    private boolean deleteRootWithoutChild (BSTNode<T> node) {
        if (node == Root && node.RightChild == null && node.LeftChild == null) {
            this.Root = null;
            return true;
        }
        return false;
    }

    private boolean deleteRootWithOneChild (BSTNode<T> node) {
        if (node != Root) return false;
        BSTNode<T> nodeChild;

        if (Root.LeftChild != null && Root.RightChild != null) {
            return false;
        }

        if (node.LeftChild != null) {
            nodeChild = node.LeftChild;
        } else {
            nodeChild = node.RightChild;
        }
        Root = nodeChild;
        nodeChild.Parent = null;

        return true;
    }

    private boolean deleteWithTwoChild (BSTNode<T> node) {
        if (node.RightChild != null && node.LeftChild != null) {
            BSTNode<T> nodeChild = FinMinMax(node.RightChild, false);

            node.NodeKey = nodeChild.NodeKey;
            node.NodeValue = nodeChild.NodeValue;

            if (nodeChild.Parent.LeftChild == nodeChild) {
                nodeChild.Parent.LeftChild = nodeChild.RightChild;
            } else {
                nodeChild.Parent.RightChild = nodeChild.RightChild;
            }

            if (nodeChild.RightChild != null) {
                nodeChild.RightChild.Parent = nodeChild.Parent;
            }
            return true;
        }

        return false;
    }

    private boolean deleteInsideTreeWithOneChild (BSTNode<T> node) {
        boolean twoChild = node.LeftChild != null && node.RightChild != null;
        boolean isLeaf = node.LeftChild == null && node.RightChild == null;

        if (twoChild || node.Parent == null || isLeaf) {
            return false;
        }

        BSTNode<T> nodeChild;
        if (node.RightChild == null) {
            nodeChild = node.LeftChild;
        } else {
            nodeChild = node.RightChild;
        }

        if (node.Parent.LeftChild == node) {
            node.Parent.LeftChild = nodeChild;
        } else {
            node.Parent.RightChild = nodeChild;
        }

        nodeChild.Parent = node.Parent;

        return true;
    }

    private boolean deleteLeafs (BSTNode<T> node) {
        if (node == Root) return false;

        if (node.LeftChild != null || node.RightChild != null) {
            return false;
        }

        if (node.Parent.LeftChild == node) {
            node.Parent.LeftChild = null;
        } else {
            node.Parent.RightChild = null;
        }

        node.Parent = null;
        return true;
    }

    public int Count() {
        return calculateCount(this.Root);
    }

    private int calculateCount(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + calculateCount(node.LeftChild) + calculateCount(node.RightChild);
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> WideAllNodes = new ArrayList<>();
        if (this.Root == null) {
            return WideAllNodes;
        }

        WideAllNodes.add(this.Root);

        if (this.Root.RightChild == null && this.Root.LeftChild == null) {
            return WideAllNodes;
        }

        return addNodeWideAllNodes(WideAllNodes, this.Root);

    }

    private ArrayList<BSTNode> addNodeWideAllNodes (ArrayList<BSTNode> WideAllNodes, BSTNode<T> thisNode) {
        if (thisNode == null) {
            return WideAllNodes;
        }

        if (thisNode.LeftChild != null) {
            WideAllNodes.add(thisNode.LeftChild);
        }

        if (thisNode.RightChild != null) {
            WideAllNodes.add(thisNode.RightChild);
        }

        addNodeWideAllNodes(WideAllNodes, thisNode.LeftChild);
        addNodeWideAllNodes(WideAllNodes, thisNode.RightChild);

        return WideAllNodes;
    }

    public ArrayList<BSTNode> DeepAllNodes(int Order) {
        ArrayList<BSTNode> DeepAllNodes = new ArrayList<>();

        if (this.Root == null) {
            return DeepAllNodes;
        }

        return addSubtree(DeepAllNodes, this.Root, Order);
    }

    private ArrayList<BSTNode> addSubtree (ArrayList<BSTNode> DeepAllNodes, BSTNode<T> thisNode, int Order) {
        if (Order == 0) {
            return addSubtreeInOrder(DeepAllNodes, thisNode);
        }

        if (Order == 1) {
            return addSubtreePostOrder(DeepAllNodes, thisNode);
        }

        return addSubtreePreOrder(DeepAllNodes, thisNode);
    }

    private ArrayList<BSTNode> addSubtreeInOrder (ArrayList<BSTNode> DeepAllNodes, BSTNode<T> thisNode) {
        if (thisNode == null) {
            return DeepAllNodes;
        }

        addSubtreeInOrder(DeepAllNodes, thisNode.LeftChild);
        DeepAllNodes.add(thisNode);
        addSubtreeInOrder(DeepAllNodes, thisNode.RightChild);

        return DeepAllNodes;
    }

    private ArrayList<BSTNode> addSubtreePostOrder (ArrayList<BSTNode> DeepAllNodes, BSTNode<T> thisNode) {
        if (thisNode == null) {
            return DeepAllNodes;
        }

        addSubtreeInOrder(DeepAllNodes, thisNode.LeftChild);
        addSubtreeInOrder(DeepAllNodes, thisNode.RightChild);
        DeepAllNodes.add(thisNode);

        return DeepAllNodes;
    }

    private ArrayList<BSTNode> addSubtreePreOrder (ArrayList<BSTNode> DeepAllNodes, BSTNode<T> thisNode) {
        if (thisNode == null) {
            return DeepAllNodes;
        }

        DeepAllNodes.add(thisNode);
        addSubtreeInOrder(DeepAllNodes, thisNode.LeftChild);
        addSubtreeInOrder(DeepAllNodes, thisNode.RightChild);

        return DeepAllNodes;
    }
}