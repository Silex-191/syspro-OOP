package my_package;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BinaryExpressionTest {

    private static class DummyBinary extends BinaryExpression {

        public DummyBinary(Expression left, Expression right) {
            super(left, right);
        }

        @Override
        protected int apply(int left, int right) {
            return left + right + 100;
        }

        @Override
        public Expression derivation(String var) {
            return this;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }

    @Test
    void testEvalDelegatesToApply() {
        Expression left = new Number(5);
        Expression right = new Number(10);
        Expression dummy = new DummyBinary(left, right);

        assertEquals(115, dummy.eval(Map.of()));
    }

    @Test
    void testIsConstant() {
        Expression constantDummy = new DummyBinary(new Number(1), new Number(2));
        assertTrue(constantDummy.isConstant());

        Expression variableDummy = new DummyBinary(new Variable("x"), new Number(2));
        assertFalse(variableDummy.isConstant());
    }

    @Test
    void testEqualsAndHashCode() {
        Expression expr1 = new DummyBinary(new Number(1), new Number(2));
        Expression expr2 = new DummyBinary(new Number(1), new Number(2));
        Expression expr3 = new DummyBinary(new Number(2), new Number(1));
        Expression expr4 = new Add(new Number(1), new Number(2));

        assertEquals(expr1, expr2);
        assertEquals(expr1.hashCode(), expr2.hashCode());

        assertNotEquals(expr1, expr3);
        assertNotEquals(expr1, expr4);
        assertNotEquals(null, expr1);
    }
}