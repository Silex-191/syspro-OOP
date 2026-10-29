package mypackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecordEntryTest {

    @Test
    void testConstructorValidSemester() {
        assertDoesNotThrow(() ->
            new RecordEntry("Math", 1, ControlType.EXAM, Grade.GOOD)
        );
    }

    @Test
    void testConstructorInvalidSemesterThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new RecordEntry("Math", -123, ControlType.EXAM, Grade.GOOD)
        );
        assertTrue(exception.getMessage().contains("-123"));

        assertThrows(IllegalArgumentException.class, () ->
            new RecordEntry("Physics", 0, ControlType.CREDIT, Grade.PASS)
        );
    }
}
