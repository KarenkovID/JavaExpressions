package expression.exceptions;

import expression.Const;
import expression.TripleExpression;

/**
 * Created by Karenkov Igor on 04.04.2016.
 */
public class ParserTest {
    public static void main(String[] args) throws ExpressionException {
        try {
            System.out.println((new ExpressionParser()).parse("5+5+5)").evaluate(-1199048046, -1737399710, -607041912));
        } catch (ParserException e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        } catch (ExpressionException e) {
            System.out.println(e);
        }
    }
}
