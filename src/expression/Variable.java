package expression;

public class Variable implements CommonExpression {
    private char type = 'x';
    public Variable(String s) {
        type = s.charAt(0);
    }

    public int evaluate(int x) {
        return x;
    }

    public double evaluate(double x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (type) {
            case 'x':
                return x;
            case 'y':
                return y;
            default:
                return z;
        }
    }
}
