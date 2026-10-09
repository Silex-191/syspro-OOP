package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubTest {

    @Test
    void testApplyAndEval() {
        Expression sub = new Sub(new Number(20), new Number(5));
        assertEquals(15, sub.eval(Map.of()));

        Expression subWithVar = new Sub(new Variable("x"), new Number(3));
        assertEquals(7, subWithVar.eval(Map.of("x", 10)));
    }

    @Test
    void testDerivation() {
        Expression sub = new Sub(new Variable("x"), new Variable("y"));
        Expression derived = sub.derivation("x");

        Expression expected = new Sub(new Number(1), new Number(0));
        assertEquals(expected, derived);
    }

    @Test
    void testSimplifyConstants() {
        Expression sub = new Sub(new Number(10), new Number(4));
        assertEquals(new Number(6), sub.simplify());
    }

    @Test
    void testSimplifyIdenticalExpressions() {
        Expression sub = new Sub(new Variable("x"), new Variable("x"));
        assertEquals(new Number(0), sub.simplify());

        Expression complexSub = new Sub(
            new Mul(new Variable("x"), new Number(2)),
            new Mul(new Variable("x"), new Number(2))
        );
        assertEquals(new Number(0), complexSub.simplify());
    }

    @Test
    void testSimplifySubtractionWithZero() {
        Expression zeroRight = new Sub(new Variable("x"), new Number(0));
        assertEquals(new Variable("x"), zeroRight.simplify());
    }

    @Test
    void testSimplifyWithUnaryMinus() {
        Expression sub = new Sub(new Variable("x"), new UnaryMinus(new Variable("y")));
        assertEquals(new Add(new Variable("x"), new Variable("y")), sub.simplify());
    }

    @Test
    void testToStringWithParenthesesAndLeftAssociativity() {
        Expression subLeftLowerPriority = new Sub(
            new Add(new Variable("x"), new Variable("y")),
            new Variable("z")
        );
        assertEquals("x + y - z", subLeftLowerPriority.toString());

        Expression subRightSamePriority = new Sub(
            new Variable("x"),
            new Sub(new Variable("y"), new Variable("z"))
        );
        assertEquals("x - (y - z)", subRightSamePriority.toString());

        Expression subLeftSamePriority = new Sub(
            new Sub(new Variable("x"), new Variable("y")),
            new Variable("z")
        );
        assertEquals("x - y - z", subLeftSamePriority.toString());
    }

    @Test
    void testGetPriority() {
        Expression sub = new Sub(new Variable("x"), new Variable("y"));
        assertEquals(OperationPriority.ADD_AND_SUB.getPriority(), sub.getPriority());
    }
}