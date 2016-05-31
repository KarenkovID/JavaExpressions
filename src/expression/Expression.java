package expression;

import expression.exceptions.ExpressionException;

public interface Expression{
    int evaluate(int x) throws ExpressionException;
}