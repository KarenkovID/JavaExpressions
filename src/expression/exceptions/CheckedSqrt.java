package expression.exceptions;

import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 11.04.2016.
 */
public class CheckedSqrt extends AbstractUnarOperation{
    public CheckedSqrt(CommonExpression expr) {
        super(expr);
    }

    @Override
    protected int specificOperation(int x) throws ExpressionException {
        if (x < 0)
            throw new ExpressionException("Negative argument in function sqrt");
        return (int)Math.sqrt(x);
    }
}
