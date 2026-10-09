package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    @Test
    void testApplyAndEval() {
        Expression add = new Add(new Number(10), new Number(15));
        assertEquals(25, add.eval(Map.of()));

        Expression addWithVar = new Add(new Variable("x"), new Number(5));
        assertEquals(15, addWithVar.eval(Map.of("x", 10)));
    }

    @Test
    void testDerivation() {
        Expression add = new Add(new Variable("x"), new Number(5));
        Expression derived = add.derivation("x");

        // (x + 5)' = x' + 5' = 1 + 0
        assertEquals(new Add(new Number(1), new Number(0)), derived);
    }

    @Test
    void testSimplifyConstants() {
        Expression add = new Add(new Number(3), new Number(4));
        assertEquals(new Number(7), add.simplify());
    }

    @Test
    void testSimplifyAdditionWithZero() {
        Expression zeroLeft = new Add(new Number(0), new Variable("x"));
        assertEquals(new Variable("x"), zeroLeft.simplify());

        Expression zeroRight = new Add(new Variable("y"), new Number(0));
        assertEquals(new Variable("y"), zeroRight.simplify());
    }

    @Test
    void testSimplifyIdenticalExpressions() {
        Expression add = new Add(new Variable("x"), new Variable("x"));
        assertEquals(new Mul(new Number(2), new Variable("x")), add.simplify());
    }

    @Test
    void testSimplifyWithUnaryMinus() {
        Expression add = new Add(new Variable("x"), new UnaryMinus(new Variable("y")));
        assertEquals(new Sub(new Variable("x"), new Variable("y")), add.simplify());
    }

    @Test
    void testToStringAndPriority() {
        Expression add = new Add(new Variable("x"), new Number(5));
        assertEquals("x + 5", add.toString());
        assertEquals(OperationPriority.ADD_AND_SUB.getPriority(), add.getPriority());
    }
}