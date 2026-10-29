package mypackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    void testNumericValues() {
        assertEquals(5, Grade.EXCELLENT.getNumericValue());
        assertEquals(4, Grade.GOOD.getNumericValue());
        assertEquals(3, Grade.SATISFACTORY.getNumericValue());
        assertEquals(2, Grade.UNSATISFACTORY.getNumericValue());
        assertEquals(0, Grade.PASS.getNumericValue());
        assertEquals(0, Grade.FAIL.getNumericValue());
    }

    @Test
    void testIsNumeric() {
        assertTrue(Grade.EXCELLENT.isNumeric());
        assertTrue(Grade.GOOD.isNumeric());
        assertTrue(Grade.SATISFACTORY.isNumeric());
        assertTrue(Grade.UNSATISFACTORY.isNumeric());

        assertFalse(Grade.PASS.isNumeric());
        assertFalse(Grade.FAIL.isNumeric());
    }
}