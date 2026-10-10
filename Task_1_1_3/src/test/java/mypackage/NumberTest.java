package mypackage;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Test
    void testEval() {
        Number number = new Number(42);
        assertEquals(42, number.eval(Map.of("x", 100)));
        assertEquals(42, number.eval(Map.of()));
    }

    @Test
    void testDerivation() {
        Number number = new Number(15);
        Expression derived = number.derivation("x");

        assertEquals(new Number(0), derived);
    }

    @Test
    void testIsConstant() {
        Number number = new Number(99);
        assertTrue(number.isConstant());
    }

    @Test
    void testGetValue() {
        Number number = new Number(-5);
        assertEquals(-5, number.getValue());
    }

    @Test
    void testEqualsAndHashCode() {
        Number num1 = new Number(10);
        Number num2 = new Number(10);
        Number num3 = new Number(20);

        assertEquals(num1, num2);
        assertEquals(num1.hashCode(), num2.hashCode());

        assertNotEquals(num1, num3);

        assertNotEquals(null, num1);
        Expression diffType = new Variable("x");
        assertNotEquals(num1, diffType);
    }

    @Test
    void testToString() {
        Number number = new Number(123);
        assertEquals("123", number.toString());
    }

    @Test
    void testGetPriority() {
        Number number = new Number(7);
        assertEquals(OperationPriority.VAR_AND_NUM.getPriority(), number.getPriority());
    }
}