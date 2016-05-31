package expression.exceptions;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;

/**
 * Created by Karenkov Igor on 26.03.2016.
 */
public class ParserException extends Exception{
    public ParserException (String str) {
        super(str);
    }
    static public ParserException createNoFirstArgument(int indArg, String str) {
        if (indArg == 0)
            return createNoFirstArgument(str);
        return new ParserException("No first argument, expect:\n\t" + str.substring(0, indArg - 1) +
                "<<missing arg>> " + str.substring(indArg) +
                "\n\t found:\n\t" + str);
    }

    static private ParserException createNoFirstArgument(String str) {
        return new ParserException("No first argument, expect: \n\t<<missing arg>> " + str +
                "\n\t found\n\t " + str);
    }

    static public ParserException createNoMiddleArgument(int indArg, String str) {
        return new ParserException("No middle argument, expect:\n\t" + str.substring(0, indArg) +
                " <<missing arg>> " + str.substring(indArg) +
                "\n\t found:\n\t" + str);
    }

    static public ParserException createNoLastArgument(String str) {
        return new ParserException("No last argument, expect:\n\t" + str +
                " <<missing arg>> "+
                "\n\t found:\n\t" + str);
    }

    static public ParserException createUnexpectedCharacterInConst (String str) {
        if (!Character.isAlphabetic(str.charAt(0)) && !Character.isAlphabetic(str.charAt(0))) {
            return new ParserException("Unexpected start character in argument:\n\t" + str);
        }
        if (!Character.isAlphabetic(str.charAt(str.length() - 1)) &&
                !Character.isAlphabetic(str.length() - 1)) {
            return new ParserException("Unexpected end character in argument:\n\t" + str);
        }
        return new ParserException("Unexpected middle character in argument:\n\t" + str);

    }
}
