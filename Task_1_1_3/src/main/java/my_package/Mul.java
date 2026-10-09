package my_package;

import java.util.Map;

/**
 * Represents a multiplication operation between two expressions.
 */
class Mul extends BinaryExpression {

    /**
     * Constructs a multiplication expression with the specified left and right operands.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Applies the multiplication operation to the evaluated integer values of the operands.
     *
     * @param left  the evaluated integer value of the left operand
     * @param right the evaluated integer value of the right operand
     * @return the product of the left and right values
     */
    @Override
    protected int apply(int left, int right) {
        return left * right;
    }

    /**
     * Computes the mathematical derivative of the multiplication expression with respect to the
     * given variable using the product rule: u'v + uv'.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code Add} expression representing the derivative
     */
    @Override
    public Expression derivation(String var) {
        return new Add(new Mul(left.derivation(var), right), new Mul(left, right.derivation(var)));
    }

    /**
     * Simplifies the multiplication expression based on algebraic rules:
     * <ul>
     *     <li>Evaluates to a constant if both operands are constants.</li>
     *     <li>Returns zero if either operand is zero.</li>
     *     <li>Returns the other operand if one operand is one.</li>
     * </ul>
     *
     * @return a simplified version of this expression, or the expression itself if no
     * simplification is possible
     */
    @Override
    public Expression simplify() {
        Expression left = this.left.simplify();
        Expression right = this.right.simplify();
        Mul simplifiedNode = new Mul(left, right);

        if (simplifiedNode.isConstant()) {
            return new Number(simplifiedNode.eval(Map.of()));
        }

        if (isZero(left) || isZero(right)) {
            return new Number(0);
        }
        if (isOne(left)) {
            return right;
        }
        if (isOne(right)) {
            return left;
        }

        return simplifiedNode;
    }

    /**
     * Gets the operation priority for multiplication.
     *
     * @return the priority level of multiplication and division operations
     */
    @Override
    public int getPriority() {
        return OperationPriority.MUL_AND_DIV.getPriority();
    }

    /**
     * Returns the string representation of the multiplication expression. Automatically encloses
     * operands in parentheses if their operation priority is strictly lower.
     *
     * @return the formatted string representing the multiplication
     */
    @Override
    public String toString() {
        String l = left.toString();
        String r = right.toString();
        if (left.getPriority() < getPriority()) {
            l = "(" + l + ")";
        }
        if (right.getPriority() < getPriority()) {
            r = "(" + r + ")";
        }
        return l + " * " + r;
    }
}