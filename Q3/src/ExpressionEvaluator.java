package Q3.src;

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
        private final double value;

        public NumberValue(Number value) {
            this.value = value.doubleValue();
        }

        @Override
        public double eval() {
            return value;
        }

        @Override
        public String toString() {
            if (value == Math.floor(value)) { // int
                DecimalFormat format = new DecimalFormat("0.#");
                return format.format(value);
            }
            return String.valueOf(value); // double
        }
    }

    static class Addition implements Expression {
        private final Expression left, right;

        public Addition(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval() {
            return left.eval() + right.eval();
        }

        @Override
        public String toString() {
            return "(" + left + " + " + right + ")";
        }
    }

    static class Multiplication implements Expression {
        private final Expression left, right;

        public Multiplication(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public double eval() {
            return left.eval() * right.eval();
        }

        @Override
        public String toString() {
            return "(" + left + " * " + right + ")";
        }
    }

    static class UnaryMinus implements Expression {
        private final Expression operand;

        public UnaryMinus(Expression operand) {
            this.operand = operand;
        }

        @Override
        public double eval() {
            return -operand.eval();
        }

        @Override
        public String toString() {
            return "(-" + operand + ")";
        }
    }
}