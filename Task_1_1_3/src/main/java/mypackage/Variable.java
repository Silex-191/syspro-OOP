package mypackage;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a named variable in a mathematical expression.
 */
class Variable extends Expression {

    private final String name;

    /**
     * Constructs a new variable expression with the specified name.
     *
     * @param name the string identifier for the variable
     */
    Variable(String name) {
        this.name = name;
    }

    /**
     * Evaluates the variable by retrieving its assigned value from the provided map.
     *
     * @param varMap a map containing variable names and their corresponding integer values
     * @return the integer value assigned to this variable
     * @throws IllegalArgumentException if the variable is not initialized in the map
     */
    @Override
    public int eval(Map<String, Integer> varMap) {
        if (!varMap.containsKey(name)) {
            throw new IllegalArgumentException("The variable " + name + " is not initialized");
        }
        return varMap.get(name);
    }

    /**
     * Computes the mathematical derivative of the variable. The derivative of a variable with
     * respect to itself is 1; with respect to any other variable is 0.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return a {@code Number} expression representing 1 or 0
     */
    @Override
    public Expression derivation(String var) {
        return this.name.equals(var) ? new Number(1) : new Number(0);
    }

    /**
     * Determines whether this expression is constant.
     *
     * @return {@code false} as a variable is never constant by definition
     */
    @Override
    public boolean isConstant() {
        return false;
    }

    /**
     * Compares this variable to another object for equality.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if both are variables and share the exact same name
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    /**
     * Generates a hash code value for this variable based on its name.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns the string representation of the variable, which is simply its name.
     *
     * @return the variable name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the operation priority for variables.
     *
     * @return the highest priority level used for terminal nodes
     */
    @Override
    public int getPriority() {
        return OperationPriority.VAR_AND_NUM.getPriority();
    }
}