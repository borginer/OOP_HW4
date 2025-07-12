package src;

import java.text.DecimalFormat;

public class ExpressionEvaluator {

    public static void main(String[] args) {
        Expression e = new Multiplication(
            new Addition(
                new NumberValue(2.5),
                new NumberValue(3.5)
            ),
            new UnaryMinus(
                new NumberValue(5)
            )
        );

        System.out.println(e.eval());      
        System.out.println(e.toString());
    }

    interface Expression {
        double eval();
        @Override
        String toString();
    }

    static class NumberValue implements Expression {
        /**
         * Abstraction Function:
         *    constant number
         * Representation Invariant:
         *    the value is not NaN
         */
        private final double value;

        public NumberValue(Number value) {
            this.value = value.doubleValue();
        }

        @Override
        public double eval() {
            checkRep();
            return value;
        }

        @Override
        public String toString() {
            checkRep();
            if (value == Math.floor(value)) { // int
                DecimalFormat format = new DecimalFormat("0.#");
                return format.format(value);
            }
            return String.valueOf(value); // double
        }

        private void checkRep() {
            assert !Double.isNaN(value);
        }
    }

    static class Addition implements Expression {
        /**
         * Abstraction Function:
         *    left + right
         * Representation Invariant:
         *    left != null && right != null
         */

        private final Expression left, right;

        public Addition(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval() {
            checkRep();
            return left.eval() + right.eval();
        }

        @Override
        public String toString() {
            checkRep();
            return "(" + left + " + " + right + ")";
        }

        private void checkRep() {
            assert left != null;
            assert right != null;
        }
    }

    static class Multiplication implements Expression {
        /**
         * Abstraction Function:
         *    left * right
         * Representation Invariant:
         *    left != null && right != null
         */
        private final Expression left, right;

        public Multiplication(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval() {
            checkRep();
            return left.eval() * right.eval();
        }

        @Override
        public String toString() {
            checkRep();
            return "(" + left + " * " + right + ")";
        }

        private void checkRep() {
            assert left != null;
            assert right != null;
        }
    }

    static class UnaryMinus implements Expression {
        /**
         * Abstraction Function:
         *    -exp
         * Representation Invariant:
         *    exp != null
         */
        private final Expression exp;

        public UnaryMinus(Expression exp) {
            this.exp = exp;
        }

        @Override
        public double eval() {
            checkRep();
            return -exp.eval();
        }

        @Override
        public String toString() {
            checkRep();
            return "(-" + exp + ")";
        }

        private void checkRep() {
            assert exp != null;
        }
    }
}