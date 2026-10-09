package my_package;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a constant integer number in a mathematical expression.
 */
public class Number extends Expression {

    private final int value;

    /**
     * Constructs a new {@code Number} expression with the specified integer value.
     *
     * @param number the integer value of this constant
     */
    public Number(int number) {
        this.value = number;
    }

    /**
     * Evaluates this expression, which always returns its constant integer value regardless of the
     * provided variables.
     *
     * @param varMap a map containing variable names and their corresponding values (ignored for
     *               constants)
     * @return the constant integer value
     */
    @Override
    public int eval(Map<String, Integer> varMap) {
        return value;
    }

    /**
     * Computes the mathematical derivative of this constant with respect to any variable. The
     * derivative of a constant is always zero.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a new {@code Number} expression representing zero
     */
    @Override
    public Expression derivation(String var) {
        return new Number(0);
    }

    /**
     * Gets the integer value of this constant.
     *
     * @return the integer value
     */
    public int getValue() {
        return value;
    }

    /**
     * Determines whether this expression is constant.
     *
     * @return {@code true} as a {@code Number} is always constant
     */
    @Override
    public boolean isConstant() {
        return true;
    }

    /**
     * Compares this {@code Number} to another object for equality. Two {@code Number} objects are
     * considered equal if they have the same integer value.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is equal to the given object; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number number = (Number) o;
        return value == number.value;
    }

    /**
     * Generates a hash code value for this {@code Number} based on its integer value.
     *
     * @return a hash code value for this expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Returns the string representation of this constant.
     *
     * @return the string value of the number
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Gets the operation priority for constants and variables.
     *
     * @return the highest priority level used for terminal nodes
     */
    @Override
    public int getPriority() {
        return OperationPriority.VAR_AND_NUM.getPriority();
    }
}