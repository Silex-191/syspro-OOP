package my_package;

import java.util.Map;
import java.util.Objects;

/**
 * Represents an abstract base class for binary mathematical expressions. It contains a left and a
 * right operand.
 */
abstract class BinaryExpression extends Expression {

    /**
     * The left operand of the binary expression.
     */
    protected final Expression left;

    /**
     * The right operand of the binary expression.
     */
    protected final Expression right;

    /**
     * Constructs a binary expression with the specified left and right operands.
     *
     * @param left  the left expression operand
     * @param right the right expression operand
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Applies the specific binary operation to the evaluated integer values of the operands.
     *
     * @param left  the evaluated integer value of the left operand
     * @param right the evaluated integer value of the right operand
     * @return the result of applying the specific operation
     */
    protected abstract int apply(int left, int right);

    /**
     * Evaluates the binary expression by first evaluating its left and right operands using the
     * provided variable map, and then applying the operation to them.
     *
     * @param variables a map containing variable names and their corresponding integer values
     * @return the evaluated integer result of the expression
     */
    @Override
    public int eval(Map<String, Integer> variables) {
        return apply(left.eval(variables), right.eval(variables));
    }

    /**
     * Determines whether this binary expression is constant (i.e., contains no variables). A binary
     * expression is constant if both its left and right operands are constant.
     *
     * @return {@code true} if the expression is strictly constant, {@code false} otherwise
     */
    @Override
    public boolean isConstant() {
        return left.isConstant() && right.isConstant();
    }

    /**
     * Compares this binary expression to another object for structural equality. Two binary
     * expressions are considered equal if they are of the exact same class and both their left and
     * right operands are strictly equal.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is structurally equal to the given object; {@code false}
     * otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryExpression that = (BinaryExpression) o;
        return left.equals(that.left) && right.equals(that.right);
    }

    /**
     * Generates a hash code value for the binary expression based on its runtime class, left
     * operand, and right operand.
     *
     * @return a hash code value for this expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}