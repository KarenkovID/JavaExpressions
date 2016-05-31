package expression.exceptions;

import expression.CommonExpression;
import expression.Subtract;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class CheckedSubtract extends Subtract{
    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    protected int specificOperation(int left, int right) throws ExpressionException{
        if (left < 0) {
            if (right > 0) {
                if (left - right > left) {
                    throw new ExpressionException("overflow");
                } else {
                    return left - right;
                }
            }
            else {
                return left - right;
            }
        }
        //left >= 0
        else {
            if (right >= 0) {
                return left - right;
            } else {
                if (right == Integer.MIN_VALUE) {
                    throw new ExpressionException("overflow");
                }
                if (Integer.MAX_VALUE - left < -right) {
                    throw new ExpressionException("overflow");
                }
                return left - right;
            }
        }

    }
}
