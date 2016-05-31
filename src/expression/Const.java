package expression;

/**
 * Created by Kerankov Igor on 14.03.2016.
 */
public class Const implements CommonExpression {
    final private int x;


    public Const(int x) {
        this.x = x;
    }

    public Const(double x) {
        this.x = (int)x;
    }

    public int evaluate(int x) {
        return (int)this.x;
    }

    public double evaluate(double x) {
        return this.x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)this.x;
    }
}
