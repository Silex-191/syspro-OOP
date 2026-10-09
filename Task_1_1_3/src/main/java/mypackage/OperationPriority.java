package mypackage;

/**
 * Defines the precedence levels for mathematical operations. Used to determine when parentheses are
 * required during expression string formatting.
 */
enum OperationPriority {
    ADD_AND_SUB(1),
    MUL_AND_DIV(2),
    UNARY_MINUS(3),
    VAR_AND_NUM(4);

    private final int priority;

    OperationPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the integer value representing the operation's priority level.
     *
     * @return the integer priority level
     */
    public int getPriority() {
        return priority;
    }
}