import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testStandardArray() {
        int[] actual = {5, 4, 1, 2, 3};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testAlreadySorted() {
        int[] actual = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testReverseSorted() {
        int[] actual = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDuplicates() {
        int[] actual = {3, 5, 3, 1, 2, 5, 2};
        int[] expected = {1, 2, 2, 3, 3, 5, 5};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testNegativeNumbers() {
        int[] actual = {-3, 10, -1, 0, 5, -8};
        int[] expected = {-8, -3, -1, 0, 5, 10};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSingleElement() {
        int[] actual = {42};
        int[] expected = {42};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testTwoElements() {
        int[] actual = {67, 52};
        int[] expected = {52, 67};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testEmptyArray() {
        int[] actual = {};
        int[] expected = {};
        Main.heapSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testNull() {
        assertDoesNotThrow(() -> Main.heapSort(null));
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}