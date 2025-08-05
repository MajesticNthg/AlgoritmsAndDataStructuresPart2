import java.util.*;


public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray (int[] a) {
        int[] isSorted = Arrays.copyOf(a, a.length);
        Arrays.sort(isSorted);

        int[] BalancedBST = new int[isSorted.length];
        BalancedBST[0] = isSorted[isSorted.length / 2];

        int indexBalancedBST = 1;
        return createBalancedBST(BalancedBST, Arrays.copyOfRange(isSorted, 0, isSorted.length / 2), Arrays.copyOfRange(isSorted, isSorted.length / 2, isSorted.length), indexBalancedBST);
    }

    private static int[] createBalancedBST (int[] BalancedBST, int[] left, int[] right, int indexBalancedBST) {
        if (indexBalancedBST > BalancedBST.length || (left.length <= 1 || right.length <= 1 )) {
            return BalancedBST;
        }


        BalancedBST[indexBalancedBST] = searchLeftChild(Arrays.copyOfRange(left, 0, left.length / 2));
        BalancedBST[indexBalancedBST + 1] = searchRightChild(Arrays.copyOfRange(right, right.length / 2, right.length));

        createBalancedBST(BalancedBST, Arrays.copyOfRange(left, 0, left.length / 2), Arrays.copyOfRange(left, left.length / 2, left.length), indexBalancedBST + 2);
        createBalancedBST(BalancedBST, Arrays.copyOfRange(right, 0, right.length / 2), Arrays.copyOfRange(right, right.length / 2, right.length), indexBalancedBST + 2);
        return BalancedBST;
    }
    private static int searchLeftChild (int[] isSorted) {
        return isSorted[isSorted.length / 2];
    }

    private static int searchRightChild (int[] isSorted) {
        return isSorted[isSorted.length / 2];
    }
}