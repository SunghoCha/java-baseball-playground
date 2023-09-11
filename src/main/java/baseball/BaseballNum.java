package baseball;

import java.util.ArrayList;
import java.util.List;

public class BaseballNum {

    public static List<Integer> createNumber() {

        List<Integer> IntegerList = new ArrayList<>();

       for (int i = 0; i < 3; i++) {
            IntegerList.add((int)(Math.random() * 9) + 1);
       }
       return IntegerList;
    }
}
