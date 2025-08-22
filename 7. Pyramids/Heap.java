import java.util.*;

class Heap {
    public int [] HeapArray;

    public Heap() { HeapArray = null; }

    public void MakeHeap (int [] a, int depth) {
        int maxSize = (int)Math.pow(2, depth + 1) - 1;
        HeapArray = new int[maxSize];
        Arrays.fill(HeapArray, -1);

        for (int x : a) {
            add(x);
        }
    }

    public int GetMax() {
        if (HeapArray == null || HeapArray[0] == -1) return -1;

        int max = HeapArray[0];

        int last = HeapArray.length - 1;

        return max;
    }

    public boolean add (int key) {
        if (HeapArray == null) return false;

        int i = 0;

        HeapArray[i] = key;

        return true;
    }
}