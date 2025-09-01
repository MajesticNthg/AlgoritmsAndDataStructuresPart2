import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;

class HeapTest {

    private Heap heap;

    @BeforeEach
    void setUp() {
        heap = new Heap();
    }

    @Test
    void testMakeHeapWithEmptyArray() {
        int[] emptyArray = {};
        heap.MakeHeap(emptyArray, 2);

        assertNotNull(heap.HeapArray);
        assertEquals(7, heap.HeapArray.length); // 2^(2+1)-1 = 7
        assertTrue(Arrays.stream(heap.HeapArray).allMatch(val -> val == -1));
    }

    @Test
    void testMakeHeapWithSingleElement() {
        int[] array = {10};
        heap.MakeHeap(array, 0);

        assertEquals(1, heap.HeapArray.length);
        assertEquals(10, heap.HeapArray[0]);
        assertTrue(heap.isCorrectHeap(heap.HeapArray));
    }

    @Test
    void testMakeHeapWithMultipleElements() {
        int[] array = {10, 5, 15, 3, 7, 12, 20};
        heap.MakeHeap(array, 2);

        assertEquals(7, heap.HeapArray.length);
        assertEquals(20, heap.HeapArray[0]);
        assertTrue(heap.isCorrectHeap(heap.HeapArray));
    }

    @Test
    void testGetMaxFromEmptyHeap() {
        heap.MakeHeap(new int[]{}, 2);
        assertEquals(-1, heap.GetMax());
    }

    @Test
    void testGetMaxFromSingleElementHeap() {
        int[] array = {42};
        heap.MakeHeap(array, 0);

        assertEquals(42, heap.GetMax());
        assertEquals(-1, heap.HeapArray[0]);
        assertEquals(-1, heap.GetMax());
    }


    @Test
    void testAddToEmptyHeap() {
        heap.MakeHeap(new int[]{}, 2);
        assertTrue(heap.Add(100));
        assertEquals(100, heap.HeapArray[0]);
        assertTrue(heap.isCorrectHeap(heap.HeapArray));
    }

    @Test
    void testAddToFullHeap() {
        int[] array = {10, 20, 30};
        heap.MakeHeap(array, 1);

        assertFalse(heap.Add(40));
        assertTrue(heap.isCorrectHeap(heap.HeapArray));
    }

    @Test
    void testAddMaintainsHeapProperty() {
        heap.MakeHeap(new int[]{10, 20, 15}, 2);

        assertTrue(heap.Add(25));
        assertTrue(heap.isCorrectHeap(heap.HeapArray));
        assertTrue(heap.HeapArray[0] == 25 || heap.HeapArray[0] == 20);
    }

    @Test
    void testAddMultipleElements() {
        heap.MakeHeap(new int[]{}, 2);

        assertTrue(heap.Add(5));
        assertTrue(heap.Add(15));
        assertTrue(heap.Add(10));
        assertTrue(heap.Add(25));
        assertTrue(heap.Add(3));

        assertTrue(heap.isCorrectHeap(heap.HeapArray));
        assertEquals(25, heap.HeapArray[0]);
    }

    @Test
    void testIsCorrectHeapWithValidHeap() {
        int[] validHeap = {20, 15, 10, 5, 7, 8, 3, -1, -1};
        assertTrue(heap.isCorrectHeap(validHeap));
    }

    @Test
    void testIsCorrectHeapWithInvalidHeap() {
        int[] invalidHeap = {10, 20, 5, -1, -1, -1, -1};
        assertFalse(heap.isCorrectHeap(invalidHeap));
    }

    @Test
    void testIsCorrectHeapWithEmptyArray() {
        assertTrue(heap.isCorrectHeap(new int[]{}));
    }

    @Test
    public void testMakeHeap() {
        Heap heap = new Heap();
        int[] arr = {3, 5, 1, 10, 2, 7};
        heap.MakeHeap(arr, 3);

        assertNotNull(heap.HeapArray);
        int maxInInput = Arrays.stream(arr).max().getAsInt();
        assertEquals(maxInInput, heap.HeapArray[0]);
    }

    @Test
    public void testGetMax() {
        Heap heap = new Heap();
        int[] arr = {3, 5, 1, 10, 2, 7};
        heap.MakeHeap(arr, 3);

        int max = heap.GetMax();
        assertEquals(10, max);

        assertTrue(heap.HeapArray[0] <= 10);
    }

    @Test
    public void testAdd() {
        Heap heap = new Heap();
        int[] arr = {3, 5, 1};
        heap.MakeHeap(arr, 3);

        boolean added = heap.Add(20);
        assertTrue(added);

        assertEquals(20, heap.HeapArray[0]);
    }

    @Test
    public void testAddToFullHeap2() {
        Heap heap = new Heap();
        int[] arr = {1,2,3,4,5,6,7};
        heap.MakeHeap(arr, 2);

        boolean added = heap.Add(100);
        assertFalse(added);
    }

    @Test
    public void testGetMaxFromEmpty() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{}, 2);
        assertEquals(-1, heap.GetMax());
    }
}
