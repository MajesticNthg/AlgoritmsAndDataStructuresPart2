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
            SearchNodeByKey(ThisNode.LeftChild, targetNode, key);
        }

        if (ThisNode.NodeKey <= key && ThisNode.RightChild != null) {
            SearchNodeByKey(ThisNode.RightChild, targetNode, key);
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

        SearchKey.Node.RightChild = targetNode;

        if (SearchKey.ToLeft) {
            SearchKey.Node.LeftChild = targetNode;
        }

        return true;
    }

    public BSTNode<T> FindMinMax (BSTNode<T> FromNode, boolean FindMax) {
        if (FindMax) {
            return SearchFindMax(FromNode);
        }

        return SearchFindMin(FromNode);
    }

    private BSTNode<T> SearchFindMax (BSTNode<T> node) {
        if (node.RightChild != null) {
            SearchFindMax(node.RightChild);
        }

        return node;
    }

    private BSTNode<T> SearchFindMin (BSTNode<T> node) {
        if (node.LeftChild != null) {
            SearchFindMin(node.LeftChild);
        }

        return node;
    }

    public boolean DeleteNodeByKey (int key) {
        BSTFind<T> SearchKey = FindNodeByKey(key);

        if (SearchKey.Node == null || !SearchKey.NodeHasKey) {
            return false;
        }

        if (SearchKey.Node == this.Root) {
            this.Root = null;
        }

        if (SearchKey.Node.LeftChild == null && SearchKey.Node.RightChild == null) {
            return deleteLeafs(SearchKey.Node);
        }

        boolean ParentLeftChild = SearchKey.Node.Parent.LeftChild == SearchKey.Node;

        return DeleteNodeByKeyAll(SearchKey.Node.Parent, SearchKey.Node.RightChild, ParentLeftChild);
    }

    private boolean deleteLeafs (BSTNode<T> node) {
        if (node.Parent.LeftChild == node) {
            node.Parent.LeftChild = null;
        } else {
            node.Parent.RightChild = null;
        }

        node.Parent = null;
        return true;
    }

    private boolean DeleteNodeByKeyAll (BSTNode<T> parent, BSTNode<T> node, boolean ParentLeftChild) {
        if (node.LeftChild != null) {
            DeleteNodeByKeyAll(parent, node.LeftChild, ParentLeftChild);
        }

        node.Parent = parent;

        if (node.RightChild == null && ParentLeftChild) {
            parent.LeftChild = node;
            return true;
        }

        return false;
    }

    public int Count() {
        return calculateCount(this.Root);
    }

    private int calculateCount(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }

        return calculateCount(node.LeftChild) + calculateCount(node.RightChild);
    }
}