package mypackage;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {

    @Test
    void testApplyAndEval() {
        Expression div = new Div(new Number(20), new Number(5));
        assertEquals(4, div.eval(Map.of()));
    }

    @Test
    void testDivisionByZeroThrowsException() {
        Expression div = new Div(new Number(10), new Number(0));
        assertThrows(ArithmeticException.class, () -> div.eval(Map.of()));
    }

    @Test
    void testDerivation() {
        Expression div = new Div(new Variable("x"), new Variable("y"));
        Expression derived = div.derivation("x");

        Expression expected = new Div(
            new Sub(
                new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0))
            ),
            new Mul(new Variable("y"), new Variable("y"))
        );
        assertEquals(expected, derived);
    }

    @Test
    void testSimplifyConstants() {
        Expression div = new Div(new Number(15), new Number(3));
        assertEquals(new Number(5), div.simplify());
    }

    @Test
    void testSimplifyZeroDividedByAnything() {
        Expression div = new Div(new Number(0), new Variable("x"));
        assertEquals(new Number(0), div.simplify());
    }

    @Test
    void testSimplifyDivisionByOne() {
        Expression div = new Div(new Variable("x"), new Number(1));
        assertEquals(new Variable("x"), div.simplify());
    }

    @Test
    void testSimplifyDivisionByZeroThrowsException() {
        Expression div = new Div(new Variable("x"), new Number(0));
        assertThrows(ArithmeticException.class, div::simplify);
    }

    @Test
    void testToStringWithParentheses() {
        Expression div1 = new Div(
            new Add(new Variable("x"), new Variable("y")),
            new Variable("z")
        );
        assertEquals("(x + y) / z", div1.toString());

        Expression div2 = new Div(
            new Variable("x"),
            new Div(new Variable("y"), new Variable("z"))
        );
        assertEquals("x / (y / z)", div2.toString());
    }
}
