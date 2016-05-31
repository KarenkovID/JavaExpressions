package expression.exceptions;

import expression.CommonExpression;
import expression.Multiply;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class CheckedMultiply extends Multiply{
    public CheckedMultiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException{
        if (left == 0 || right == 0)
            return 0;
        if (left > right) {
            int x = right;
            right = left;
            left = x;
        }
        if (left < 0) {
            if (right < 0) {
                if (left == Integer.MIN_VALUE || Integer.MAX_VALUE / left > right) {
                    throw new ExpressionException("overflow");
                }
                return left * right;
            } else {
                if (left == -1) {
                    return right * left;
                }
                if (Integer.MIN_VALUE / left < right) {
                    throw new ExpressionException("overflow");
                }
                return right * left;
            }
        } else {
            if (Integer.MAX_VALUE / right < left)
                throw new ExpressionException("overflow");
            return left * right;
        }
    }
}
