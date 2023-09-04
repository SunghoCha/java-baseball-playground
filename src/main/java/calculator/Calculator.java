package calculator;



import java.util.Scanner;

public class Calculator {

    private final ExpressionCheck expressionCheck;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        ExpressionCheck ec = ExpressionCheck.create(value);
        Calculator calculator = Calculator.create(ec);
        double result = calculator.calculate();
        System.out.println(result);
    }

    private Calculator(ExpressionCheck expressionCheck) {
        this.expressionCheck = expressionCheck;
    }

    public static Calculator create(ExpressionCheck expressionCheck) {
        return new Calculator(expressionCheck);
    }

    public double calculate() {

        String expression = this.expressionCheck.getExpression();

        String[] parsingStr= expression.split(" ");

        double result = Double.parseDouble(parsingStr[0]);
        String operator = "";
        for (int i = 1; i < parsingStr.length; i++) {

            Double operand;
            if ( i % 2 != 0) {
                operator = parsingStr[i];
                continue;
            }
            operand = Double.parseDouble(parsingStr[i]);
            result = Operator.findBySymbol(operator).apply(result, operand);
        }
        return result;
    }
}
