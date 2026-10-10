package mypackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationPriorityTest {

    @Test
    void testEnumValuesAndPriorities() {
        assertEquals(1, OperationPriority.ADD_AND_SUB.getPriority());
        assertEquals(2, OperationPriority.MUL_AND_DIV.getPriority());
        assertEquals(3, OperationPriority.UNARY_MINUS.getPriority());
        assertEquals(4, OperationPriority.VAR_AND_NUM.getPriority());
    }

    @Test
    void testPriorityComparisons() {
        assertTrue(OperationPriority.MUL_AND_DIV.getPriority()
            > OperationPriority.ADD_AND_SUB.getPriority());

        assertTrue(OperationPriority.VAR_AND_NUM.getPriority()
            > OperationPriority.UNARY_MINUS.getPriority());
    }
}