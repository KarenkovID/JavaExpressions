package expression.exceptions;

import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class CheckedNegate extends AbstractUnarOperation{
    public CheckedNegate(CommonExpression expr) {
        super(expr);
    }

    @Override
    protected int specificOperation(int x) throws ExpressionException{
        if (x == Integer.MIN_VALUE)
            throw new ExpressionException("overflow");
        return -x;
    }
}
