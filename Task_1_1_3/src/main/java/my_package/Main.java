package my_package;

/**
 * The main entry point of the application used for demonstrating and testing the functionality of
 * mathematical expressions.
 */
public class Main {

    /**
     * The main method that executes a series of tests for expression creation, differentiation,
     * evaluation, equality comparison, parsing, and simplification.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Expression e1 = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e1.print();

        Expression de = e1.derivation("x");
        de.print();
        System.out.println(de.simplify());

        Expression e2 = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        int result = e2.eval("x = 10; y = 13");
        System.out.println(result);

        Expression exprBase = new Add(new Number(5), new Number(6));
        Expression exprSame = new Add(new Number(5), new Number(6));
        Expression exprSwaped = new Add(new Number(6), new Number(5));
        System.out.println(exprBase.equals(exprSame));
        System.out.println(exprBase.equals(exprSwaped));

        Expression parsedExpr = Expression.parse("3 + 2 * x");
        parsedExpr.print();

        Expression r1 = Expression.parse("x * 0").simplify();
        System.out.println("x * 0 -> " + r1);

        Expression r2 = Expression.parse("1 * y").simplify();
        System.out.println("1 * y -> " + r2);

        Expression r3 = Expression.parse("0 + z").simplify();
        System.out.println("0 + z -> " + r3);

        Expression r4 = Expression.parse("(x * 2) - (x * 2)").simplify();
        System.out.println("(x * 2) - (x * 2) -> " + r4);

        Expression r5 = Expression.parse("10 + 15 * 2").simplify();
        System.out.println("10 + 15 * 2 -> " + r5);
    }
}