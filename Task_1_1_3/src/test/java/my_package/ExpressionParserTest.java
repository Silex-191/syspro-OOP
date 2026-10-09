package my_package;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    void testParseConstantsAndVariables() {
        assertEquals(new Number(123), Expression.parse("123"));
        assertEquals(new Variable("xyz"), Expression.parse("xyz"));
    }

    @Test
    void testParseBasicOperations() {
        assertEquals(new Add(new Number(1), new Number(2)), Expression.parse("1 + 2"));
        assertEquals(new Sub(new Variable("x"), new Number(5)), Expression.parse("x - 5"));
        assertEquals(new Mul(new Number(3), new Variable("y")), Expression.parse("3 * y"));
        assertEquals(new Div(new Number(10), new Number(2)), Expression.parse("10 / 2"));
    }

    @Test
    void testParseUnaryMinus() {
        assertEquals(new UnaryMinus(new Number(5)), Expression.parse("-5"));
        assertEquals(new UnaryMinus(new Variable("x")), Expression.parse("-x"));

        Expression expected = new UnaryMinus(new Add(new Number(1), new Number(2)));
        assertEquals(expected, Expression.parse("-(1 + 2)"));
    }

    @Test
    void testParsePriority() {
        Expression expectedMulFirst = new Add(
            new Number(1),
            new Mul(new Number(2), new Number(3))
        );
        assertEquals(expectedMulFirst, Expression.parse("1 + 2 * 3"));

        Expression expectedAddFirst = new Mul(
            new Add(new Number(1), new Number(2)),
            new Number(3)
        );
        assertEquals(expectedAddFirst, Expression.parse("(1 + 2) * 3"));
    }

    @Test
    void testParseLeftAssociativity() {
        Expression expectedSub = new Sub(
            new Sub(new Number(1), new Number(2)),
            new Number(3)
        );
        assertEquals(expectedSub, Expression.parse("1 - 2 - 3"));

        Expression expectedDiv = new Div(
            new Div(new Number(8), new Number(4)),
            new Number(2)
        );
        assertEquals(expectedDiv, Expression.parse("8 / 4 / 2"));
    }

    @Test
    void testWhitespaceHandling() {
        Expression expected = new Add(new Number(1), new Number(2));
        assertEquals(expected, Expression.parse("  1   + \t 2 \n "));
    }

    @Test
    void testParseInvalidExpressionsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Expression.parse(null));
        assertThrows(IllegalArgumentException.class, () -> Expression.parse(""));
        assertThrows(IllegalArgumentException.class, () -> Expression.parse("   "));

        assertThrows(IllegalArgumentException.class, () -> Expression.parse("1 +"));
        assertThrows(IllegalArgumentException.class, () -> Expression.parse("-"));
        assertThrows(IllegalArgumentException.class, () -> Expression.parse("("));

        assertThrows(IllegalArgumentException.class, () -> Expression.parse("(1 + 2"));

        assertThrows(IllegalArgumentException.class, () -> Expression.parse("& 1"));
    }
}