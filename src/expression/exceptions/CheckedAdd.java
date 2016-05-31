package expression.exceptions;

import expression.Add;
import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression left, CommonExpression right) {
        super(left, right);
    }
    @Override
    protected int specificOperation(int left, int right) throws ExpressionException{
        if (left > right) {
            int x = right;
            right = left;
            left = x;
        }
        if (left < 0) {
            if (right >= 0) {
                return left + right;
            } else {
                if (left < left + right)
                    throw new ExpressionException("overflow");
                return left + right;
            }
        }
        else {
            if (left > left + right)
                throw new ExpressionException("overflow");
            return left + right;
        }
    }
}
