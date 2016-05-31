package expression.exceptions;

import expression.CommonExpression;
import expression.Divide;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException{
        if (right == 0)
            throw new ExpressionException("Division by zero");
        if (left == Integer.MIN_VALUE && right == -1)
            throw new ExpressionException("overflow");
        return left / right;
    }
}
