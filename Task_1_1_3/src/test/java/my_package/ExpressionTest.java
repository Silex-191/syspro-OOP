package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    private static class DummyExpression extends Expression {

        @Override
        public int eval(Map<String, Integer> varMap) {
            return varMap.values().stream().mapToInt(Integer::intValue).sum();
        }

        @Override
        public Expression derivation(String var) {
            return this;
        }

        @Override
        public int getPriority() {
            return 1;
        }

        @Override
        public boolean isConstant() {
            return false;
        }
    }

    @Test
    void testEvalWithStringAssignments() {
        Expression expr = new DummyExpression();

        int result = expr.eval("x=10; y=20; z=5");
        assertEquals(35, result);
    }

    @Test
    void testEvalWithEmptyOrNullString() {
        Expression expr = new DummyExpression();

        assertEquals(0, expr.eval(""));
        assertEquals(0, expr.eval((String) null));
        assertEquals(0, expr.eval("   "));
    }

    @Test
    void testIsZero() {
        assertTrue(Expression.isZero(new Number(0)));

        assertFalse(Expression.isZero(new Number(1)));
        assertFalse(Expression.isZero(new Variable("x")));
        assertFalse(Expression.isZero(new DummyExpression()));
    }

    @Test
    void testIsOne() {
        assertTrue(Expression.isOne(new Number(1)));

        assertFalse(Expression.isOne(new Number(0)));
        assertFalse(Expression.isOne(new Number(-1)));
        assertFalse(Expression.isOne(new Variable("y")));
        assertFalse(Expression.isOne(new DummyExpression()));
    }
}