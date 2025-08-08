import java.util.*;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray (int[] a) {
        int[] isSorted = Arrays.copyOf(a, a.length);
        Arrays.sort(isSorted);
        int height = calculateDepth(isSorted.length);
        int size = (int) (Math.pow(2, height + 1) - 1);
        Integer[] BalancedBST = new Integer[size];
        int indexBalancedBST = 0;

        return createBalancedBST(BalancedBST, indexBalancedBST, isSorted, 0, isSorted.length - 1);
    }

    private static int[] createBalancedBST (Integer[] BalancedBST, int indexBalancedBST, int[] isSorted, int leftSubTree, int rightSubTree) {
        if (leftSubTree > rightSubTree) {
            return createFinalBalancedBST(BalancedBST);
        }

        int mid = (leftSubTree + rightSubTree) / 2;
        BalancedBST[indexBalancedBST] = isSorted[mid];

        createBalancedBST(BalancedBST, 2 * indexBalancedBST + 1, isSorted, leftSubTree, mid - 1);
        createBalancedBST(BalancedBST, 2 * indexBalancedBST + 2, isSorted, mid + 1, rightSubTree);

        return createFinalBalancedBST(BalancedBST);
    }

    private static int calculateDepth (int isSortedSize) {
        if (isSortedSize <= 0) {
            return 0;
        }

        int result_depth = 0;

        for (int i = 0; i < isSortedSize; i++) {
            result_depth = result_depth * 2 + 1;
            if (result_depth > isSortedSize) {
                return i;
            }
        }

        return result_depth;
    }

    private static int[] createFinalBalancedBST (Integer[] BalancedBST) {
        int[] finalBalancedBST = new int[calculateMassiveSize(BalancedBST)];

        int indexBalancedBST = 0;
        for (Integer integer : BalancedBST) {
            if (integer != null) {
                finalBalancedBST[indexBalancedBST] = integer;
                indexBalancedBST++;
            }
        }

        return finalBalancedBST;
    }

    private static int calculateMassiveSize(Integer[] BalancedBST) {
        int size = 0;

        for (Integer integer : BalancedBST) {
            if (integer != null) {
                size++;
            }
        }
        return size;
    }
}
