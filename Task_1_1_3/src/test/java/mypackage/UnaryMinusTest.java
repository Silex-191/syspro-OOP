package mypackage;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UnaryMinusTest {

    @Test
    void testEval() {
        Expression unaryMinus = new UnaryMinus(new Number(5));
        assertEquals(-5, unaryMinus.eval(Map.of()));

        Expression unaryWithVar = new UnaryMinus(new Variable("x"));
        assertEquals(-10, unaryWithVar.eval(Map.of("x", 10)));
    }

    @Test
    void testDerivation() {
        Expression unaryMinus = new UnaryMinus(new Variable("x"));
        Expression derived = unaryMinus.derivation("x");

        assertEquals(new UnaryMinus(new Number(1)), derived);
    }

    @Test
    void testIsConstant() {
        Expression constantMinus = new UnaryMinus(new Number(10));
        assertTrue(constantMinus.isConstant());

        Expression variableMinus = new UnaryMinus(new Variable("x"));
        assertFalse(variableMinus.isConstant());
    }

    @Test
    void testSimplifyConstants() {
        Expression unaryMinus = new UnaryMinus(new Number(7));
        assertEquals(new Number(-7), unaryMinus.simplify());
    }

    @Test
    void testSimplifyDoubleNegation() {
        Expression doubleNegation = new UnaryMinus(new UnaryMinus(new Variable("x")));
        assertEquals(new Variable("x"), doubleNegation.simplify());
    }

    @Test
    void testEqualsAndHashCode() {
        Expression um1 = new UnaryMinus(new Variable("x"));
        Expression um2 = new UnaryMinus(new Variable("x"));
        Expression um3 = new UnaryMinus(new Variable("y"));

        assertEquals(um1, um2);
        assertEquals(um1.hashCode(), um2.hashCode());

        assertNotEquals(um1, um3);
        assertNotEquals(null, um1);
        assertNotEquals(new Variable("x"), um1);
    }

    @Test
    void testToStringWithParentheses() {
        Expression minusAdd = new UnaryMinus(new Add(new Variable("x"), new Variable("y")));
        assertEquals("-(x + y)", minusAdd.toString());

        Expression minusVar = new UnaryMinus(new Variable("x"));
        assertEquals("-x", minusVar.toString());
    }

    @Test
    void testGetPriority() {
        Expression unaryMinus = new UnaryMinus(new Variable("x"));
        assertEquals(OperationPriority.UNARY_MINUS.getPriority(), unaryMinus.getPriority());
    }
}