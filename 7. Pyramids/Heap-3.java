import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class HeapTest {

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

        boolean added = heap.add(20);
        assertTrue(added);

        assertEquals(20, heap.HeapArray[0]);
    }

    @Test
    public void testAddToFullHeap() {
        Heap heap = new Heap();
        int[] arr = {1,2,3,4,5,6,7};
        heap.MakeHeap(arr, 2);

        boolean added = heap.add(100);
        assertFalse(added);
    }

    @Test
    public void testGetMaxFromEmpty() {
        Heap heap = new Heap();
        heap.MakeHeap(new int[]{}, 2);
        assertEquals(-1, heap.GetMax());
    }
}