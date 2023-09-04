package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규식 이해 부족
 * https://terry9611.tistory.com/333 내용 인용함
 */
public class ExpressionCheck {
    private final String expression;

    public static ExpressionCheck create(String expression) {

        if(!validateExpression(expression)) {
            throw new IllegalArgumentException("올바른 연산식이 아닙니다.");
        }

        return new ExpressionCheck(expression);
    }

    private static boolean validateExpression(String expression) {
        Pattern pattern = Pattern.compile("^[+\\-]?\\d( [+\\-*/] \\d)*$");
//        Pattern pattern = Pattern.compile("^[+\\-*/\\d]*$");
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }

    private ExpressionCheck(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
