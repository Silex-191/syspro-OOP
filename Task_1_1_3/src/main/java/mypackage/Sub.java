package mypackage;

import java.util.Map;

/**
 * Represents a subtraction operation between two expressions.
 */
class Sub extends BinaryExpression {

    /**
     * Constructs a subtraction expression with the specified left (minuend) and right (subtrahend)
     * operands.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Applies the subtraction operation to the evaluated integer values of the operands.
     *
     * @param left  the evaluated integer value of the left operand
     * @param right the evaluated integer value of the right operand
     * @return the difference between the left and right values
     */
    @Override
    protected int apply(int left, int right) {
        return left - right;
    }

    /**
     * Computes the mathematical derivative of the subtraction expression with respect to the given
     * variable.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code Sub} expression representing the derivative
     */
    @Override
    public Expression derivation(String var) {
        return new Sub(left.derivation(var), right.derivation(var));
    }

    /**
     * Simplifies the subtraction expression based on algebraic rules:
     * <ul>
     *     <li>Evaluates to a constant if both operands are constants.</li>
     *     <li>Returns zero if the left and right operands are structurally equal.</li>
     *     <li>Returns the left operand if the right operand is zero.</li>
     *     <li>Converts the subtraction of a unary minus into an addition.</li>
     * </ul>
     *
     * @return a simplified version of this expression, or the expression itself if no
     * simplification is possible
     */
    @Override
    public Expression simplify() {
        Expression left = this.left.simplify();
        Expression right = this.right.simplify();
        Sub simplifiedNode = new Sub(left, right);

        if (simplifiedNode.isConstant()) {
            return new Number(simplifiedNode.eval(Map.of()));
        }

        if (left.equals(right)) {
            return new Number(0);
        }
        if (isZero(right)) {
            return left;
        }

        if (right instanceof UnaryMinus unaryMinus) {
            return new Add(left, unaryMinus.expression);
        }
        return simplifiedNode;
    }

    /**
     * Gets the operation priority for subtraction.
     *
     * @return the priority level of addition and subtraction operations
     */
    @Override
    public int getPriority() {
        return OperationPriority.ADD_AND_SUB.getPriority();
    }

    /**
     * Returns the string representation of the subtraction expression. Automatically encloses
     * operands in parentheses if their operation priority is strictly lower, or if the right
     * operand has the same priority (to respect left-associativity).
     *
     * @return the formatted string representing the subtraction
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
        return l + " - " + r;
    }
}