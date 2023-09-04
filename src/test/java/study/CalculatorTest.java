package study;
import calculator.Calculator;
import calculator.Operator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest {

//    Scanner sc = new Scanner(System.in);
//    String value = sc.nextLine();
//    String[] values = value.split(value);

//    private Calculator cal = new Calculator();



//    @Test
//    void test() {
//
//        String str = "2 + 3 * 4 / 2";
//        String intStr = str.replaceAll("[^\\d]", "");
//        String s = str.replaceAll("[^\\D]" , "").replaceAll("\\s","");
//        System.out.println(intStr);
//        System.out.println(s);
//        String[] str2 = str.split(" ");
//        System.out.println("str2 = " + str2);
//
//
//
//        Double result = Double.parseDouble(str2[0]);
//        for (int i = 1; i < str2.length; i++) {
//            String operator = "";
//            Double operand;
//            if ( i % 2 != 0) {
//                operator = str2[i];
//                continue;
//            }
//
//            operand = Double.parseDouble(str2[i]);
//            result = calculate(result,operand,operator);
//
//        }
//
//
//    }

    
    @Test
    void enumTest() {

        Operator bySymbol = Operator.findBySymbol("+");
        System.out.println("bySymbol = " + bySymbol);

        String str = "2 + 3 * 4 / 2";
        String[] str2 = str.split(" ");

        Double result = Double.parseDouble(str2[0]);
        String operator = "";
        for (int i = 1; i < str2.length; i++) {

            Double operand;
            if ( i % 2 != 0) {
                operator = str2[i];
                continue;
            }
            operand = Double.parseDouble(str2[i]);
            result = Operator.findBySymbol(operator).apply(result, operand);
        }
        //then
        assertThat(result).isEqualTo(10);

    }

    @Test
    void regTest() {
        String regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
        String data = "Asdf@naver.cccom";

        boolean result = Pattern.matches(regExp, data);
    }
}
