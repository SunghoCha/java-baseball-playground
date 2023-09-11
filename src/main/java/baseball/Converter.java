package baseball;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private final List<Integer> IntegerList;

    private static List<Integer> convert(String value) {
        String[] values = value.split("");
        digitCheck(values);
        return getIntegers(values);
    }

    private static List<Integer> getIntegers(String[] values) {
        List<Integer> IntegerList = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            IntegerList.add(Integer.parseInt(values[i]));
        }
        return IntegerList;
    }

    private static void digitCheck(String[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("세 자리수를 입력해주세요.");
        }
    }

    public Converter(String value) {
        this.IntegerList = convert(value);
    }

    public List<Integer> getIntegerList() {
        return IntegerList;
    }
}
