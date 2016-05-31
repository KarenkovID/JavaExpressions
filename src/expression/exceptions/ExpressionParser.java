package expression.exceptions;

import expression.*;
import javafx.util.Pair;

/**
 * Recursive descent parser class
 * Expression parser parse string expression and return TripleExpression that may be evaluated
 *
 * Complexity O(n)
 */
public class ExpressionParser implements Parser {
    private class Result {
        CommonExpression acc;
        int beginRest;

        private Result(CommonExpression expr, int last) {
            acc = expr;
            beginRest = last;
        }
    }
    private String originalStr;
    private StringBuilder str;
    private int curPos = 0;
    private final StringBuilder x = new StringBuilder("x");
    private final StringBuilder y = new StringBuilder("y");
    private final StringBuilder z = new StringBuilder("z");

    public TripleExpression parse(String str) throws ParserException {
        this.str = new StringBuilder();
        this.originalStr = str;
        boolean wasNum = false;
        boolean wasPr = false;
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (Character.isSpaceChar(ch) || ch == '\t' || ch == '\n') {
                wasPr = true;
            } else if (Character.isDigit(ch)) {
                if (wasNum && wasPr) {
                    throw new ParserException("haven't operator");
                }
                wasPr = false;
                wasNum = true;
                this.str.append(ch);
            } else {
                wasNum = wasPr = false;
                this.str.append(ch);
            }
        }
        if (this.str.length() == 0)
            throw new ParserException("empty input");
        try {
            Result res = plusMinus(0);
            if (res.beginRest == this.str.length()) {
                return res.acc;
            }
            //TODO: table of pos
            if (this.str.charAt(res.beginRest) == ')') {
                throw new ParserException("No open brackets in pos");
            }
            if (res.beginRest + 1 == this.str.length()) {
                throw new ParserException("Incorrect end symbol: " + this.str.charAt(res.beginRest));
            }
            throw new ParserException("Incorrect middle symbol: " + this.str.charAt(res.beginRest));
        } catch (ParserException e) {
            throw e;
        }
    }

    private Result plusMinus(int beginStr) throws ParserException{
        haveMore(beginStr);
        boolean isNeg = false;
        while (beginStr < str.length() && str.charAt(beginStr) == '-') {
            isNeg = !isNeg;
            ++beginStr;
        }
        Result res;
        if (isNeg && Character.isDigit(str.charAt(beginStr))) {
            res = mulDiv(beginStr - 1);
        } else {
            res = mulDiv(beginStr);
            if (isNeg) {
                res.acc = new CheckedNegate(res.acc);
            }
        }

        char ch;
        while (res.beginRest < str.length()) {
            ch = str.charAt(res.beginRest);
            if (ch != '+' && ch != '-') {
                break;
            }
            Result right;
            if (ch == '-') {
                int i = res.beginRest + 1;
                isNeg = false;
                while (i < str.length() && str.charAt(i) == '-') {
                    ++i;
                    isNeg = !isNeg;
                }
                if (i == res.beginRest + 1) {
                    right = mulDiv(i);
                }else {
                    if (isNeg && Character.isDigit(str.charAt(i))) {
                        right = mulDiv(i - 1);
                    } else {
                        right = mulDiv(i);
                        if (isNeg) {
                            right.acc = new CheckedNegate(right.acc);
                        }
                    }
                }
                res.acc = new CheckedSubtract(res.acc, right.acc);
            } else {
                right = mulDiv(res.beginRest + 1);
                res.acc = new CheckedAdd(res.acc, right.acc);
            }
            res.beginRest = right.beginRest;
        }
        return res;

    }

    private Result mulDiv(int beginStr) throws ParserException {
        haveMore(beginStr);
        Result res = bracket(beginStr);
        while (true) {
            if (res.beginRest == str.length()) {
                return res;
            }
            char ch = str.charAt(res.beginRest);
            if (ch != '*' && ch != '/')
                return res;
            Result right = bracket(res.beginRest + 1);
            if (ch == '*') {
                res.acc = new CheckedMultiply(res.acc, right.acc);
            } else {
                res.acc = new CheckedDivide(res.acc, right.acc);
            }
            res.beginRest = right.beginRest;
        }
    }

    private Result bracket(int beginStr) throws ParserException {
        haveMore(beginStr);
        int oldBegin = beginStr;
        Result res;
        boolean isNeg = false;
        while (beginStr < str.length() && str.charAt(beginStr) == '-') {
            isNeg = !isNeg;
            ++beginStr;
        }
        if (str.charAt(beginStr) ==  '(') {
            try {
                res = plusMinus(beginStr + 1);
                if (res.beginRest < str.length() && str.charAt(res.beginRest) == ')') {
                    res.beginRest++;
                } else {
                    throw new ParserException("No close bracket");
                }
                if (isNeg)
                    res.acc = new CheckedNegate(res.acc);
                return res;
            } catch (ParserException e) {
                throw e;
            }
        }
        return varOrFunction(oldBegin);
    }

    private Result varOrFunction(int beginStr) throws ParserException {
        haveMore(beginStr);
        Result res;
        int oldBegin = beginStr;
        StringBuilder f = new StringBuilder();
        boolean isNeg = false;
        while (beginStr < str.length() && str.charAt(beginStr) == '-') {
            isNeg = !isNeg;
            ++beginStr;
        }
        int i = beginStr;
        while (i < str.length() && Character.isLetter(str.charAt(i)) && i < beginStr + 3) {
            f.append(str.charAt(i));
            i++;
        }
        if (f.length() != 0) {
            if (f.length() == 1) {
                if (f.charAt(0) == 'x') {
                    res = new Result(new Variable("x"), i);
                } else if (f.charAt(0) == 'y') {
                    res = new Result(new Variable("y"), i);
                } else if (f.charAt(0) == 'z') {
                    res = new Result(new Variable("z"), i);
                } else {
                    throw new ParserException("Incorrect variable name: " + f);
                }
                if (isNeg)
                    res.acc = new CheckedNegate(res.acc);
                return res;
            } else if (i < str.length()) {
                if (f.toString().equalsIgnoreCase("abs")) {
                    res = bracket(i);
                    res.acc = new CheckedAbs(res.acc);
                    if (isNeg)
                        res.acc = new CheckedNegate(res.acc);
                    return res;
                } else if (f.toString().equalsIgnoreCase("sqr") && str.charAt(i) == 't' && ++i < str.length()) {
                    res = bracket(i);
                    res.acc = new CheckedSqrt(res.acc);
                    if (isNeg)
                        res.acc = new CheckedNegate(res.acc);
                    return res;
                } else {
                    throw new ParserException("Incorrect function name: " + f);
                }
            } else {
                throw new ParserException("Invalid function");
            }
        }
        char ch = str.charAt(beginStr);
        if (ch == '-' || ch == '+' || ch == '*' || ch == '/') {
            if (beginStr == 0) {
                throw ParserException.createNoFirstArgument(0, str.toString());
            }
            if (str.charAt(beginStr - 1) == '(') {
                throw ParserException.createNoFirstArgument(beginStr, str.toString());
            }
            throw ParserException.createNoMiddleArgument(beginStr, str.toString());
        }
        return num(oldBegin);
    }

    private Result num(int beginStr) throws ParserException{
        haveMore(beginStr);
        StringBuilder s = new StringBuilder();
        int i = beginStr;
        boolean isNeg = false;
        boolean isCorrect = true;
        while (i < str.length() && str.charAt(i) == '-') {
            isNeg = !isNeg;
            ++i;
        }
        if (i == str.length())
            throw new ParserException("argument doesn't exist");
        char ch = str.charAt(i);
        if (isNeg)
            s.append('-');
        while (i < str.length() && !(ch == '-' || ch == '+' || ch == '*'
                || ch == '/' || ch == '(' || ch == ')')) {
            if (!Character.isDigit(ch)) {
                isCorrect = false;
            }
            s.append(ch);
            i++;
            if (i < str.length()) {
                ch = str.charAt(i);
            }
        }
        if (!isCorrect)
            throw ParserException.createUnexpectedCharacterInConst(s.toString());
        int n;
        try {
            n = Integer.parseInt(s.toString());
        } catch (NumberFormatException e) {
            throw new ParserException("Overflow constant: " + s);
        }
        return new Result(new Const(n), i);
    }
    private void haveMore(int x) throws ParserException {
        if (x >= str.length())
            throw  ParserException.createNoLastArgument(str.toString());
    }

}
