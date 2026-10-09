package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MulTest {

    @Test
    void testApplyAndEval() {
        Expression mul = new Mul(new Number(6), new Number(7));
        assertEquals(42, mul.eval(Map.of()));

        Expression mulWithVar = new Mul(new Variable("x"), new Number(3));
        assertEquals(30, mulWithVar.eval(Map.of("x", 10)));
    }

    @Test
    void testDerivation() {
        Expression mul = new Mul(new Variable("x"), new Variable("y"));
        Expression derived = mul.derivation("x");

        Expression expected = new Add(
            new Mul(new Number(1), new Variable("y")),
            new Mul(new Variable("x"), new Number(0))
        );

        assertEquals(expected, derived);
    }

    @Test
    void testSimplifyConstants() {
        Expression mul = new Mul(new Number(4), new Number(5));
        assertEquals(new Number(20), mul.simplify());
    }

    @Test
    void testSimplifyMultiplicationByZero() {
        Expression zeroLeft = new Mul(new Number(0), new Variable("x"));
        assertEquals(new Number(0), zeroLeft.simplify());

        Expression zeroRight = new Mul(new Variable("y"), new Number(0));
        assertEquals(new Number(0), zeroRight.simplify());
    }

    @Test
    void testSimplifyMultiplicationByOne() {
        Expression oneLeft = new Mul(new Number(1), new Variable("x"));
        assertEquals(new Variable("x"), oneLeft.simplify());

        Expression oneRight = new Mul(new Variable("y"), new Number(1));
        assertEquals(new Variable("y"), oneRight.simplify());
    }

    @Test
    void testToStringWithParentheses() {
        Expression mulLeftLowerPriority = new Mul(
            new Add(new Variable("x"), new Variable("y")),
            new Variable("z")
        );
        assertEquals("(x + y) * z", mulLeftLowerPriority.toString());

        Expression mulRightLowerPriority = new Mul(
            new Variable("x"),
            new Sub(new Variable("y"), new Variable("z"))
        );
        assertEquals("x * (y - z)", mulRightLowerPriority.toString());

        Expression mulNoParens = new Mul(new Variable("x"), new Variable("y"));
        assertEquals("x * y", mulNoParens.toString());
    }

    @Test
    void testGetPriority() {
        Expression mul = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(OperationPriority.MUL_AND_DIV.getPriority(), mul.getPriority());
    }
}