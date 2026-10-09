package my_package;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a unary minus operation applied to a single mathematical expression.
 */
class UnaryMinus extends Expression {

    /**
     * The inner expression to which the unary minus is applied.
     */
    protected final Expression expression;

    /**
     * Constructs a unary minus expression for the specified inner expression.
     *
     * @param expression the mathematical expression to negate
     */
    public UnaryMinus(Expression expression) {
        this.expression = expression;
    }

    /**
     * Evaluates the unary minus expression by negating the evaluated result of its inner
     * expression.
     *
     * @param varMap a map containing variable names and their corresponding integer values
     * @return the negated evaluated integer result
     */
    @Override
    public int eval(Map<String, Integer> varMap) {
        return -expression.eval(varMap);
    }

    /**
     * Computes the mathematical derivative of the unary minus expression.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code UnaryMinus} expression representing the negated derivative
     */
    @Override
    public Expression derivation(String var) {
        return new UnaryMinus(expression.derivation(var));
    }

    /**
     * Determines whether this unary minus expression is constant.
     *
     * @return {@code true} if the inner expression is constant, {@code false} otherwise
     */
    @Override
    public boolean isConstant() {
        return expression.isConstant();
    }

    /**
     * Simplifies the unary minus expression based on algebraic rules:
     * <ul>
     *     <li>Removes double negation (e.g., --x becomes x).</li>
     *     <li>Evaluates to a constant number if the inner expression is constant.</li>
     * </ul>
     *
     * @return a simplified version of this expression, or a new simplified {@code UnaryMinus}
     * expression
     */
    @Override
    public Expression simplify() {
        Expression simplified = this.expression.simplify();
        if (simplified instanceof UnaryMinus unaryMinus) {
            return unaryMinus.expression; // --x -> x
        }
        if (simplified.isConstant()) {
            return new Number(-simplified.eval(Map.of()));
        }
        return new UnaryMinus(simplified);
    }

    /**
     * Compares this unary minus expression to another object for equality.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if both are unary minus expressions and their inner expressions are
     * equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnaryMinus that = (UnaryMinus) o;
        return expression.equals(that.expression);
    }

    /**
     * Generates a hash code value for this unary minus expression based on its inner expression.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    /**
     * Gets the operation priority for the unary minus.
     *
     * @return the priority level of unary minus operations
     */
    @Override
    public int getPriority() {
        return OperationPriority.UNARY_MINUS.getPriority();
    }

    /**
     * Returns the string representation of the unary minus expression. Automatically encloses the
     * inner expression in parentheses if its priority is strictly lower.
     *
     * @return the formatted string representing the negated expression
     */
    @Override
    public String toString() {
        if (expression.getPriority() < getPriority()) {
            return "-(" + expression + ")";
        }
        return "-" + expression;
    }
}