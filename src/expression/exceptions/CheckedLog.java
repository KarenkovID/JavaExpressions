package expression.exceptions;

import expression.AbstractBinaryOperation;
import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 11.04.2016.
 */
public class CheckedLog extends AbstractBinaryOperation {

    public CheckedLog(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected int specificOperation(int left, int right) throws ExpressionException {
        if (right == 0)
            throw new ExpressionException("Log from zero");
        return (int)(Math.log(left)/Math.log(right));
    }

    @Override
    protected double specificOperation(double left, double right) {
        return 0;
    }
}
