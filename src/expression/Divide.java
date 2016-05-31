package expression;

import expression.exceptions.ExpressionException;

public class Divide extends AbstractBinaryOperation {
    public Divide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException {
        assert right != 0;
        return left / right;
    }

    protected double specificOperation(double left, double right) {
        assert right != 0;
        return left / right;
    }
}
