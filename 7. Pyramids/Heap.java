import java.util.*;

class Heap
{
    public int [] HeapArray;

    public Heap() { HeapArray = null; }

    public void MakeHeap (int [] a, int depth)
    {
        HeapArray = new int[calculateSizeHeap(depth)];
        Arrays.fill(HeapArray, -1);

        for (int i : a) {
            Add(i);
        }
    }

    private int calculateSizeHeap (int depth) {
        if (depth <= 0) {
            return 0;
        }

        int result_depth = 0;

        for (int i = 0; i < depth; i++) {
            result_depth = result_depth * 2 + 1;
            if (result_depth > depth) {
                return result_depth;
            }
        }
        return result_depth;
    }

    public int GetMax()
    {
        if (HeapArray == null) {
            return -1;
        }

        if (HeapArray.length == 0 || HeapArray[0] == -1) {
            return -1;
        }

        int max = HeapArray[0];

        for (int i = HeapArray.length - 1; i >= 0; i--) {
            if (HeapArray[i] != -1) {
                HeapArray[0] = HeapArray[i];
                HeapArray[i] = -1;
                newRoot(0);
                break;
            }
        }
        return max;
    }

    private void newRoot (int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int isChange = i;

        if (leftChild < HeapArray.length && HeapArray[leftChild] != -1 && HeapArray[leftChild] > HeapArray[isChange]) {
            isChange = leftChild;
        }

        if (rightChild < HeapArray.length && HeapArray[rightChild] != -1 && HeapArray[rightChild] > HeapArray[isChange]) {
            isChange = rightChild;
        }

        if (isChange != i && HeapArray[isChange] != -1) {
            int reserve = HeapArray[i];
            HeapArray[i] = HeapArray[isChange];
            HeapArray[isChange] = reserve;
            newRoot(isChange);
        }

    }

    public boolean Add (int key) {
        if (HeapArray == null) {
            return false;
        }

        for (int i = 0; i < HeapArray.length; i++) {
            if (HeapArray[i] == -1) {
                HeapArray[i] = key;
                insertKey(key, i);
                return true;
            }
        }

        return false;
    }

    private void insertKey(int key, int i) {
        for (; i > 0; i--) {
            int parent = (i - 1) / 2;
            if (HeapArray[i] > HeapArray[parent]) {
                i = moveUp(key, i, parent) + 1;
            } else break;
        }
    }

    private int moveUp (int key, int i, int parent) {
        int reserve = HeapArray[i];
        HeapArray[i] = HeapArray[parent];
        HeapArray[parent] = reserve;
        return parent;
    }

    public boolean isCorrectHeap (int[] HeapArray) {
        for (int x = 0; x < HeapArray.length; x++) {
            int leftChild = 2 * x + 1;
            int rightChild = 2 * x + 2;

            if (HeapArray[x] == -1) {
                continue;
            }
            if (leftChild < HeapArray.length && HeapArray[leftChild] != -1 && HeapArray[x] < HeapArray[leftChild]) {
                return false;
            }
            if (rightChild < HeapArray.length && HeapArray[rightChild] != -1 && HeapArray[x] < HeapArray[rightChild]) {
                return false;
            }
        }
        return true;
    }
}
