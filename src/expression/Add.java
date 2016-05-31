package expression;

import expression.exceptions.ExpressionException;

public class Add extends AbstractBinaryOperation {
    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException {
        return left + right;
    }

    protected double specificOperation(double left, double right) {
        return left + right;
    }
}
