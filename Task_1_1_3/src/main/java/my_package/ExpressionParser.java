package my_package;

/**
 * A utility class for parsing string representations of mathematical expressions into an
 * {@code Expression} abstract syntax tree using a recursive descent parser.
 */
class ExpressionParser {

    private final String str;
    private int pos = 0;

    private ExpressionParser(String str) {
        this.str = str;
    }

    /**
     * Parses a mathematical expression string and returns the corresponding {@code Expression}
     * object.
     *
     * @param expression the string containing the mathematical expression to parse
     * @return the root node of the parsed {@code Expression} tree
     * @throws IllegalArgumentException if the expression is null, blank, or contains invalid
     *                                  syntax
     */
    public static Expression parse(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("Empty expression");
        }
        return new ExpressionParser(expression.trim()).parseExpression(0);
    }

    private Expression parseExpression(int priority) {
        Expression left = parsePrimary();
        while (true) {
            skipSpaces();
            if (pos >= str.length()) {
                return left;
            }

            char op = str.charAt(pos);
            int opPriority = getPriority(op);

            if (opPriority < priority) {
                return left;
            }
            pos++;

            Expression right = parseExpression(opPriority + 1);
            left = makeBinary(op, left, right);
        }
    }

    private Expression makeBinary(char op, Expression left, Expression right) {
        return switch (op) {
            case '+' -> new Add(left, right);
            case '-' -> new Sub(left, right);
            case '*' -> new Mul(left, right);
            case '/' -> new Div(left, right);
            default -> throw new IllegalStateException("Unknown operation: " + op);
        };
    }

    private Expression parsePrimary() {
        skipSpaces();
        if (pos >= str.length()) {
            throw new IllegalArgumentException("Unexpected end of expression");
        }

        char c = str.charAt(pos);

        if (c == '(') {
            pos++;
            Expression exp = parseExpression(0);
            skipSpaces();
            if (pos >= str.length() || str.charAt(pos) != ')') {
                throw new IllegalArgumentException("Expected ')' at pos " + pos);
            }
            pos++;
            return exp;
        }

        if (c == '-') {
            pos++;
            return new UnaryMinus(parsePrimary());
        }

        if (Character.isDigit(c)) {
            int start = pos;
            while (pos < str.length() && Character.isDigit(str.charAt(pos))) {
                pos++;
            }
            return new Number(Integer.parseInt(str.substring(start, pos)));
        }

        if (Character.isLetter(c)) {
            int start = pos;
            while (pos < str.length() && Character.isLetterOrDigit(str.charAt(pos))) {
                pos++;
            }
            return new Variable(str.substring(start, pos));
        }

        throw new IllegalArgumentException("Unexpected character '" + c + "' at pos " + pos);
    }

    private void skipSpaces() {
        while (pos < str.length() && Character.isWhitespace(str.charAt(pos))) {
            pos++;
        }
    }

    private int getPriority(char c) {
        if (c == '+' || c == '-') {
            return OperationPriority.ADD_AND_SUB.getPriority();
        }
        if (c == '*' || c == '/') {
            return OperationPriority.MUL_AND_DIV.getPriority();
        }
        return -1;
    }
}