package expression.exceptions;

import expression.AbstractBinaryOperation;
import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 11.04.2016.
 */
public class CheckedPow extends AbstractBinaryOperation {

    public CheckedPow(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int specificOperation(int left, int right) throws ExpressionException {
        double res = Math.pow(left, right);
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY)
            throw new ExpressionException("Overflow");
        return (int) res;
    }

    @Override
    protected double specificOperation(double left, double right) {
        return 0;
    }
}
