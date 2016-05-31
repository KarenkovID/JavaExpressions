package expression;

import expression.exceptions.ExpressionException;

/**
 * Created by Игорь on 14.03.2016.
 */
abstract public class AbstractBinaryOperation implements CommonExpression {
    private CommonExpression left, right;

    public AbstractBinaryOperation(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract int specificOperation(int left, int right) throws ExpressionException;

    protected abstract double specificOperation(double left, double right);

    final public int evaluate(int x) throws ExpressionException {
        return specificOperation(left.evaluate(x), right.evaluate(x));
    }

    final public double evaluate(double x) {
        return specificOperation(left.evaluate(x), right.evaluate(x));
    }

    final public int evaluate(int x, int y, int z) throws ExpressionException{
        return specificOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }


}
