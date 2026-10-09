package mypackage;

import java.util.Map;

/**
 * Represents a division operation between two expressions.
 */
class Div extends BinaryExpression {

    /**
     * Constructs a division expression with the specified left (dividend) and right (divisor)
     * operands.
     *
     * @param left  the left expression operand (dividend)
     * @param right the right expression operand (divisor)
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Applies the division operation to the evaluated integer values of the operands.
     *
     * @param left  the evaluated integer value of the left operand
     * @param right the evaluated integer value of the right operand
     * @return the quotient of the left and right values
     * @throws ArithmeticException if the right operand is zero
     */
    @Override
    protected int apply(int left, int right) {
        if (right == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left / right;
    }

    /**
     * Computes the mathematical derivative of the division expression with respect to the given
     * variable using the quotient rule: (u'v - uv') / v^2.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code Div} expression representing the derivative
     */
    @Override
    public Expression derivation(String var) {
        return new Div(
            new Sub(
                new Mul(left.derivation(var), right),
                new Mul(left, right.derivation(var))
            ),
            new Mul(right, right)
        );
    }

    /**
     * Simplifies the division expression based on algebraic rules:
     * <ul>
     *     <li>Evaluates to a constant if both operands are constants.</li>
     *     <li>Returns zero if the left operand (dividend) is zero.</li>
     *     <li>Returns the left operand if the right operand (divisor) is one.</li>
     * </ul>
     *
     * @return a simplified version of this expression, or the expression itself if no
     * simplification is possible
     * @throws ArithmeticException if the simplified right operand evaluates to zero
     */
    @Override
    public Expression simplify() {
        Expression left = this.left.simplify();
        Expression right = this.right.simplify();

        if (isZero(right)) {
            throw new ArithmeticException("Division by zero");
        }

        Div simplifiedNode = new Div(left, right);
        if (simplifiedNode.isConstant()) {
            return new Number(simplifiedNode.eval(Map.of()));
        }

        if (isZero(left)) {
            return new Number(0);
        }
        if (isOne(right)) {
            return left;
        }

        return simplifiedNode;
    }

    /**
     * Gets the operation priority for division.
     *
     * @return the priority level of multiplication and division operations
     */
    @Override
    public int getPriority() {
        return OperationPriority.MUL_AND_DIV.getPriority();
    }

    /**
     * Returns the string representation of the division expression. Automatically encloses operands
     * in parentheses if their operation priority is strictly lower, or if the right operand has the
     * same or lower priority (to respect left-associativity).
     *
     * @return the formatted string representing the division
     */
    @Override
    public String toString() {
        String l = left.toString();
        String r = right.toString();
        if (left.getPriority() < getPriority()) {
            l = "(" + l + ")";
        }
        if (right.getPriority() <= getPriority()) {
            r = "(" + r + ")";
        }
        return l + " / " + r;
    }
}