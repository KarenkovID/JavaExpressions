package expression.exceptions;

import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 11.04.2016.
 */
public class CheckedAbs extends AbstractUnarOperation {
    public CheckedAbs(CommonExpression expr) {
        super(expr);
    }

    @Override
    protected int specificOperation(int x) throws ExpressionException {
        if (x == Integer.MIN_VALUE)
            throw new ExpressionException("Overflow in function abs");
        if (x < 0)
            return -x;
        return x;
    }
}
