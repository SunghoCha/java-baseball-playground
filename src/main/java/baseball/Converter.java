package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Converter {

    private static final Pattern NUM_PATTERN = Pattern.compile("\\d{3}");
    private final List<Integer> player;

    public static Converter validateNumber(String value) {
        Matcher matcher = NUM_PATTERN.matcher(value);
        if (!matcher.find()) {
            throw new IllegalArgumentException("올바른 입력이 아닙니다");
        }
        return new Converter(ToIntegerList(split(value)));
    }

    private Converter(List<Integer> player) {
        this.player = player;
    }

    private static String[] split(String value) {
        String[] values = value.split("");
        return values;
    }

    private static List<Integer> ToIntegerList(String[] values) {
        return Arrays.stream(values).map(s->Integer.parseInt(s)).collect(Collectors.toList());
    }

    public List<Integer> getPlayer() {
        return player;
    }
}

