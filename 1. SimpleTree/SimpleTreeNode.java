import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild (SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        if (ParentNode == null) {
            return;
        }

        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode (SimpleTreeNode<T> NodeToDelete)
    {
        if (NodeToDelete == this.Root) {
            Root = null;
        } else {
            NodeToDelete.Parent.Children.remove(NodeToDelete);
        }

    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        List<SimpleTreeNode<T>> AllNodes = new ArrayList<>();

        if (Root == null) return AllNodes;

        return CreateAllNodes(Root, AllNodes);
    }

    private List<SimpleTreeNode<T>> CreateAllNodes (SimpleTreeNode<T> Root, List<SimpleTreeNode<T>> AllNodes) {
        AllNodes.add(Root);

        if (Root.Children == null) return AllNodes;

        for (SimpleTreeNode<T> Child : Root.Children) {
            CreateAllNodes(Child, AllNodes);
        }

        return AllNodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue (T val)
    {
        List<SimpleTreeNode<T>> IsFindNodes = new ArrayList<>();
        if (Root == null) return IsFindNodes;

        if (Root.NodeValue.equals(val)) {
            IsFindNodes.add(Root);
        }
        return FindCorrectNodes(Root, IsFindNodes, val);
    }

    private List<SimpleTreeNode<T>> FindCorrectNodes (SimpleTreeNode<T> Root, List<SimpleTreeNode<T>> IsFindNodes, T val) {
        if (Root.Children == null) return IsFindNodes;

        for (SimpleTreeNode<T> Child : Root.Children) {
            if (Child.NodeValue.equals(val)) {
                IsFindNodes.add(Child);
            }
            FindCorrectNodes(Child, IsFindNodes, val);
        }

        return IsFindNodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        if (OriginalNode == null || NewParent == null) {
            return;
        }

        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count()
    {
        return GetAllNodes().size();
    }

    public int LeafCount()
    {
        if (Root == null) return 0;

        List<SimpleTreeNode<T>> Leafs = new ArrayList<>();
        if (Root.Children == null || Root.Children.isEmpty()) {
            Leafs.add(Root);
        }

        return CalculateLeafCount(Root, Leafs);
    }

    private int CalculateLeafCount (SimpleTreeNode<T> Root, List<SimpleTreeNode<T>> Leafs) {

        for (SimpleTreeNode<T> Child : Root.Children) {
            if (Child.Children == null || Child.Children.isEmpty()) {
                Leafs.add(Child);
            }
            CalculateLeafCount(Child, Leafs);
        }

        return Leafs.size();
    }
}
