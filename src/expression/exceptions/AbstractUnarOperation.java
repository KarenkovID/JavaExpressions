package expression.exceptions;

import expression.CommonExpression;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public abstract class AbstractUnarOperation implements CommonExpression {
    private CommonExpression expr;

    public AbstractUnarOperation(CommonExpression expr) {
        this.expr = expr;
    }

    public double evaluate(double x) {
        return 0;
    }

    public int evaluate(int x) throws ExpressionException {
        return 0;
    }

    public int evaluate(int x, int y, int z) throws ExpressionException {
        return specificOperation(expr.evaluate(x, y, z));
    }

    protected abstract int specificOperation(int x) throws ExpressionException;
}
