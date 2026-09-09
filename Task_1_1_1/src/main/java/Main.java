import java.util.Arrays;

/**
 * Class with the task solution
 */
public class Main {

    /**
     * Prevents instantiation of this utility class.
     */
    private Main() {}

    /**
     * Swaps two elements in the specified array.
     *
     * @param arr the array in which elements are to be swapped
     * @param i   the index of the first element
     * @param j   the index of the second element
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sifts down an element at the specified index to preserve the max-heap property.
     *
     * @param arr  the array representing the heap
     * @param len  the active boundary of the heap
     * @param node the index of the element to sift down
     */
    public static void siftDown(int[] arr, int len, int node) {
        while (node * 2 + 1 < len) {
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            int largest = node;

            if (arr[left] > arr[largest]) largest = left;
            if (right < len && arr[right] > arr[largest]) largest = right;

            if (largest != node) {
                swap(arr, node, largest);
                node = largest;
            }
            else break;
        }
    }

    /**
     * Builds a max-heap from an unsorted array in place.
     *
     * @param arr the array to convert into a max-heap
     */
    public static void heapify(int[] arr) {
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            siftDown(arr, arr.length, i);
        }
    }

    /**
     * Sorts an array of integers in ascending order using the heapsort algorithm.
     *
     * @param arr the array to be sorted
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        heapify(arr);
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            swap(arr, 0, len - i);
            siftDown(arr, len - i, 0);
        }
    }

    /**
     * Application entry point for testing and demonstration.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 1, 2, 3};
        heapSort(arr);
        System.out.print(Arrays.toString(arr));
    }
}
