package my_package;

import java.util.Map;

/**
 * Represents an addition operation between two expressions.
 */
public class Add extends BinaryExpression {

    /**
     * Constructs an addition expression with the specified left and right operands.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Applies the addition operation to the evaluated integer values of the operands.
     *
     * @param left  the evaluated integer value of the left operand
     * @param right the evaluated integer value of the right operand
     * @return the sum of the left and right values
     */
    @Override
    protected int apply(int left, int right) {
        return left + right;
    }

    /**
     * Computes the mathematical derivative of the addition expression with respect to the given
     * variable. The derivative of a sum is the sum of the derivatives of its operands.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code Add} expression representing the derivative
     */
    @Override
    public Expression derivation(String var) {
        return new Add(left.derivation(var), right.derivation(var));
    }

    /**
     * Simplifies the addition expression based on algebraic rules:
     * <ul>
     *     <li>Evaluates to a constant if both operands are constants.</li>
     *     <li>Returns the other operand if one operand is zero.</li>
     *     <li>Replaces the addition of identical operands with multiplication by 2.</li>
     *     <li>Converts the addition of a unary minus into a subtraction.</li>
     * </ul>
     *
     * @return a simplified version of this expression, or the expression itself if no
     * simplification is possible
     */
    @Override
    public Expression simplify() {
        Expression left = this.left.simplify();
        Expression right = this.right.simplify();
        Add simplifiedNode = new Add(left, right);

        if (simplifiedNode.isConstant()) {
            return new Number(simplifiedNode.eval(Map.of()));
        }

        if (isZero(left)) {
            return right;
        }
        if (isZero(right)) {
            return left;
        }

        if (left.equals(right)) {
            return new Mul(new Number(2), left);
        }

        if (right instanceof UnaryMinus unaryMinus) {
            return new Sub(left, unaryMinus.expression);
        }
        return simplifiedNode;
    }

    /**
     * Gets the operation priority for addition.
     *
     * @return the priority level of addition and subtraction operations
     */
    @Override
    public int getPriority() {
        return OperationPriority.ADD_AND_SUB.getPriority();
    }

    /**
     * Returns the string representation of the addition expression. Automatically encloses operands
     * in parentheses if their operation priority is strictly lower.
     *
     * @return the formatted string representing the addition
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
        return l + " + " + r;
    }
}