package expression;

import expression.exceptions.ExpressionException;

/**
 * Created by Karenkov Igor on 14.03.2016.
 */
public class Multiply extends AbstractBinaryOperation {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException {
        return left * right;
    }

    protected double specificOperation(double left, double right) {
        return left * right;
    }
}
