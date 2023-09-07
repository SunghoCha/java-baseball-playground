package study;

import baseball.Converter;
import baseball.Counter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CounterTest {

    
    @ParameterizedTest
    @DisplayName("for문_안에서_포함개수_체크하는_함수")
    @CsvSource({"5,1", "1,0", "5,1"})
    void CheckContainment(int num, int expected) {
        List<Integer> computer = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> player = new ArrayList<>(Arrays.asList(1, 2, 3));
        Counter counter = new Counter();

        counter.checkContainment(computer,num);
        assertThat(counter.getCount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("for문_돌려서_포함개수_체크하는_함수")
    void countContainment() {
        List<Integer> computer = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> player = new ArrayList<>(Arrays.asList(1, 2, 3));
        Counter counter = new Counter();

        counter.countContainment(computer,player);
        assertThat(counter.getCount()).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("for문_안에서_스트라이크_체크하는_함수")
    @CsvSource({"0,0", "1,0", "2,1"})
    void CheckStrike(int idx, int expected) {
        List<Integer> computer = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> player = new ArrayList<>(Arrays.asList(1, 2, 3));
        Counter counter = new Counter();

        counter.countContainment(computer,player);
        counter.checkStrike(computer,player, idx);
        assertThat(counter.getScCount()).isEqualTo(expected);

        //getter를 test에서만 쓰는 상황인데 없애기가 쉽지 않음
        //scCount값은 다른 함수(countStrike)에서 반환해서 사용하는데 단위테스트에 scCount값 얻자고 그걸 여기 넣을수도 없고...
    }

    @Test
    @DisplayName("for문_돌려서_스트라이크개수_체크하는_함수")
    void countStrike() {

        List<Integer> computer = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> player = new ArrayList<>(Arrays.asList(1, 2, 3));
        Counter counter = new Counter();
        int count = counter.countContainment(computer, player);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void counterBall() {
        List<Integer> computer = new ArrayList<>(Arrays.asList(7, 9, 6));
        List<Integer> player = new ArrayList<>(Arrays.asList(9, 7, 6));
        Counter counter = new Counter();
        counter.setComputer(computer);

        counter.countContainment(computer, player);

        counter.countStrike(computer, player);

        counter.countBall(player);

        counter.getComputerNum();

    }

    private static final Pattern NUM_PATTERN = Pattern.compile("\\d{3}");

    @ParameterizedTest
    @CsvSource({"123,true", "11,false", "aaa,false"})
        //모든 경우의 수 어떻게 test하지?
    void extractNum(String value, boolean expected) {

        Matcher matcher = NUM_PATTERN.matcher(value);

        assertThat(matcher.find()).isEqualTo(expected);

    }

    @ParameterizedTest
    @ValueSource(strings = {"1","13","aa"})
    void Converter(String value) {
        assertThrows(IllegalArgumentException.class, () ->Converter.validateNumber(value));
    }


    @Test
    void containTest() {
        List<Integer> computer = new ArrayList<>(Arrays.asList(1, 4, 7));
        List<Integer> player = new ArrayList<>(Arrays.asList(1, 2, 3));

        Counter counter = new Counter();
        counter.setComputer(computer);
        HashSet<Integer> set = new HashSet<>();
        int[] intArrPlayer = player.stream().mapToInt(i -> i).toArray();
        set = counter.makeSet(intArrPlayer, set);


        Integer[] filteredPlayer = set.toArray(new Integer[0]);
        int[] intArrplayer = Arrays.stream(filteredPlayer).mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < intArrplayer.length; i++) {
            counter.checkContainment(computer,intArrplayer[i]);
        }


    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    private void make(int expected) {
        int[] intArr = new int[3];
        for (int i = 0; i < 3; i++) {
            intArr[i] = (int) (Math.random() * 10);
        }
        assertThat(intArr[expected]).isGreaterThanOrEqualTo(0);
        assertThat(intArr[expected]).isLessThanOrEqualTo(9);

    }
}





//    int[] intArr = new int[3];
//    @Test
//    public void Check() {
//        Converter converter = Converter.validateNumber("123");
//
//        do {
//            converter.ma();
//        } while (intArr[0] == 0);
//
//        assertThat(intArr[0]==0).isFalse();
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(ints = {0,1,2})
//    private void make(int expected) {
//        for (int i = 0; i < 3; i++) {
//            intArr[i] = (int) (Math.random() * 10);
//        }
//        assertThat(intArr[expected]).isGreaterThanOrEqualTo(0);
//        assertThat(intArr[expected]).isLessThanOrEqualTo(9);
//
//    }
//}