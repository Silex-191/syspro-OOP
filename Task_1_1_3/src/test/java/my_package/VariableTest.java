package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void testEvalSuccess() {
        Variable variable = new Variable("x");
        assertEquals(15, variable.eval(Map.of("x", 15, "y", 20)));
    }

    @Test
    void testEvalThrowsExceptionWhenNotInitialized() {
        Variable variable = new Variable("z");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> variable.eval(Map.of("x", 10))
        );
        assertEquals("The variable z is not initialized", exception.getMessage());
    }

    @Test
    void testDerivation() {
        Variable x = new Variable("x");

        assertEquals(new Number(1), x.derivation("x"));

        assertEquals(new Number(0), x.derivation("y"));
    }

    @Test
    void testIsConstant() {
        Variable variable = new Variable("x");
        assertFalse(variable.isConstant());
    }

    @Test
    void testEqualsAndHashCode() {
        Variable var1 = new Variable("x");
        Variable var2 = new Variable("x");
        Variable var3 = new Variable("y");

        assertEquals(var1, var2);
        assertEquals(var1.hashCode(), var2.hashCode());

        assertNotEquals(var1, var3);
        assertNotEquals(null, var1);
        Expression one = new Number(1);
        assertNotEquals(one, var1);
    }

    @Test
    void testToString() {
        Variable variable = new Variable("myVar");
        assertEquals("myVar", variable.toString());
    }

    @Test
    void testGetPriority() {
        Variable variable = new Variable("x");
        assertEquals(OperationPriority.VAR_AND_NUM.getPriority(), variable.getPriority());
    }
}