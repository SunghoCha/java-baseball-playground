package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.StringIndexOutOfBoundsException;

import static org.assertj.core.api.Assertions.*;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    /**
     * 요구사항 1
     * "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
     * "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
     */

    @Test
    void split() {
        //given
        String str = "1,2";

        //when
        String[] actual = str.split(",");

        //then
        assertThat(actual).contains("1","2");
        assertThat(actual).contains("1");
        assertThat(actual).containsExactly("1","2");
//        assertThat(actual).containsExactly("1");

    }

    @Test
    void split2() {
        //given
        String str = "1";

        //when
        String[] actual = str.split(",");

        //then
        assertThat(actual).contains("1");
        assertThat(actual).containsExactly("1");


    }

    /**
     * 요구사항 2
     * "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
     */

    @Test
    void split3() {
        //given
        String str = "(1,2)";

        //when
        String subStr = str.substring(1,4);
        String[] actual = subStr.split(",");

        //then
        assertThat(actual).contains("1","2");
        assertThat(actual).contains("1");
        assertThat(actual).containsExactly("1","2");
    }

    /**
     * 요구사항 3
     * "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
     * String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
     * JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
     */

    @Test
    @DisplayName("특정 위치 문자 가져오기")
    void charAtTest() {
        //given
        String str = "abc";

        //when
        char a0 = str.charAt(0);
        char a1 = str.charAt(1);
        char a2 = str.charAt(2);

        //then
        assertThat(a0).isEqualTo('a');
        assertThat(a1).isEqualTo('b');
        assertThat(a2).isEqualTo('c');
    }

    @Test
    @DisplayName("StringIndexOutOfBoundsException 테스트")
    void boundsExcptionTest() {
        //given
        String str = "abc";

        //when

        //then
        assertThatThrownBy(() -> str.charAt(str.length())).isInstanceOf(StringIndexOutOfBoundsException.class);
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> str.charAt(str.length()));
    }

}
