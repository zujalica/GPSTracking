package zuja.buzz.gpstracking.utils;

import android.text.TextUtils;

/**
 * Util class for user input validation. Mainly used with regular expressions.
 */
public class ValidationUtils {

    private final static String onlyTextRegex = "[a-zA-Z]+";
    private final static String textWithSpaceRegex = "[a-zA-Z\\s]+";
    private final static String textWithDashesRegex = "[a-zA-Z\\-]+";
    private final static String alphaNumericWithDashes = "[a-zA-Z\\d\\-]+";

    public static boolean isTextOnly(String input){
        return input.matches(onlyTextRegex);
    }

    public static boolean isNumbersOnly(String input){
        return TextUtils.isDigitsOnly(input);
    }

    public static boolean checkInputWithRegex(String input, String regex){
        return input.matches(regex);
    }

    public static boolean isTextWithdashes(String input){
        return input.matches(textWithDashesRegex);
    }

    public static boolean isTextWithSpaces(String input){
        return input.matches(textWithSpaceRegex);
    }

    public static boolean isAlphaNumericWithdashes(String input){
        return input.matches(alphaNumericWithDashes);
    }
}
