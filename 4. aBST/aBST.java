import java.util.*;

class aBST {
    public Integer Tree [];

    public aBST (int depth) {
        int tree_size = calculateDepth(depth);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) {
            Tree[i] = null;
        }
    }

    private int calculateDepth (int depth) {
        if (depth < 0) {
            return 0;
        }

        if (depth == 0) {
            return 1;
        }

        int result_depth = 1;

        for (int i = 0; i < depth; i++) {
            result_depth = result_depth * 2 + 1;
        }

        return result_depth;
    }

    public Integer FindKeyIndex (int key) {
        if (Tree.length == 0) {
            throw new IllegalStateException("Пустое дерево");
        }

        int i = 0;
        while (i < Tree.length) {
            if (Tree[i] == null) {
                return -i;
            }
            if (Tree[i] == key) {
                return i;
            }
            if (Tree[i] < key) {
                i = 2 * i + 2;
                continue;
            }
            if (Tree[i] > key) {
                i = 2 * i + 1;
            }
        }
        return null;
    }

    public int AddKey (int key) {
        if (Tree.length == 0) {
            throw new IllegalStateException("Пустое дерево");
        }

        int i = 0;
        while (i < Tree.length) {
            if (Tree[i] == null) {
                Tree[i] = key;
                return i;
            }
            if (Tree[i] == key) {
                return i;
            }
            if (Tree[i] < key) {
                i = 2 * i + 2;
                continue;
            }
            if (Tree[i] > key) {
                i = 2 * i + 1;
            }
        }
        return -1;
    }

    public ArrayList<Integer> WideAllNodes() {
        ArrayList<Integer> WideAllNodes = new ArrayList<>();

        if (Tree.length == 0) {
            return WideAllNodes;
        }

        int i = 0;
        while (i < Tree.length) {
            if (Tree[i] != null) {
                WideAllNodes.add(Tree[i]);
            }
            i++;
        }

        return WideAllNodes;
    }

}
