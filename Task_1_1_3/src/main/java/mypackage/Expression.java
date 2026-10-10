package mypackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an abstract base class for all mathematical expressions. Provides foundational methods
 * for parsing, evaluation, differentiation, and simplification.
 */
public abstract class Expression {

    /**
     * Parses a string representation of a mathematical expression into an {@code Expression}
     * object.
     *
     * @param str the string containing the mathematical expression
     * @return the parsed {@code Expression} tree
     */
    public static Expression parse(String str) {
        return ExpressionParser.parse(str);
    }

    /**
     * Prints the string representation of this expression to the standard output.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Evaluates the expression using a string containing variable assignments. The assignments
     * should be formatted as key-value pairs separated by semicolons (e.g., "x=10; y=13").
     *
     * @param str the string containing the variable assignments
     * @return the evaluated integer result of the expression
     * @throws IllegalArgumentException if a variable in the expression is not initialized in the
     *                                  parsed map
     * @throws ArithmeticException      if an arithmetic error occurs during evaluation (e.g.,
     *                                  division by zero)
     */
    public int eval(String str) {
        Map<String, Integer> map = new HashMap<>();
        if (str != null && !str.trim().isEmpty()) {
            String[] parts = str.split(";");
            for (String part : parts) {
                String[] kv = part.split("=");
                if (kv.length == 2) {
                    map.put(kv[0].trim(), Integer.parseInt(kv[1].trim()));
                }
            }
        }
        return eval(map);
    }

    /**
     * Evaluates the expression using a map of variable assignments.
     *
     * @param varMap a map containing variable names and their corresponding integer values
     * @return the evaluated integer result of the expression
     * @throws IllegalArgumentException if a variable in the expression is not initialized in the
     *                                  provided map
     * @throws ArithmeticException      if an arithmetic error occurs during evaluation (e.g.,
     *                                  division by zero)
     */
    public abstract int eval(Map<String, Integer> varMap);

    /**
     * Computes the symbolic derivative of the expression with respect to the given variable.
     *
     * @param var the name of the variable to differentiate with respect to
     * @return a new {@code Expression} representing the derivative
     */
    public abstract Expression derivation(String var);

    /**
     * Gets the operation priority of this expression. Used for determining when parentheses are
     * necessary during string representation.
     *
     * @return the integer priority level of the operation
     */
    public abstract int getPriority();

    /**
     * Determines whether this expression is a constant (i.e., contains no variables).
     *
     * @return {@code true} if the expression evaluates to a constant, {@code false} otherwise
     */
    public abstract boolean isConstant();

    /**
     * Simplifies the expression based on algebraic rules. The base implementation returns the
     * expression itself without modifications.
     *
     * @return a simplified version of this expression
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Checks if the given expression is strictly the constant number zero.
     *
     * @param e the expression to check
     * @return {@code true} if the expression is an instance of {@code Number} with value 0,
     * {@code false} otherwise
     */
    protected static boolean isZero(Expression e) {
        return e instanceof Number n && n.getValue() == 0;
    }

    /**
     * Checks if the given expression is strictly the constant number one.
     *
     * @param e the expression to check
     * @return {@code true} if the expression is an instance of {@code Number} with value 1,
     * {@code false} otherwise
     */
    protected static boolean isOne(Expression e) {
        return e instanceof Number n && n.getValue() == 1;
    }
}