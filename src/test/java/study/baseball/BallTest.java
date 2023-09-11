package study.baseball;

import baseball.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class BallTest {
    private Ball computer1;
    private Ball computer2;
    private Ball computer3;

    private List<Integer> computers;
    private List<Integer> players;

    @BeforeEach
    void setUp() {
        computer1 = new Ball(1, 1);
        computer2 = new Ball(2, 2);
        computer3 = new Ball(3, 3);
        computers = Arrays.asList(1, 2, 3);
        players = Arrays.asList(4, 1, 3);
    }

    @Test
    void 숫자_일대일_낫싱_테스트() {
        Ball player = new Ball(2,5);
        assertThat(computer1.play(player)).isEqualTo(BallStatus.NOTHING);
    }

    @Test
    void 숫자_일대일_볼_테스트() {
        Ball player = new Ball(2,1);
        assertThat(computer1.play(player)).isEqualTo(BallStatus.BALL);
    }

    @Test
    void 숫자_일대일_스트라이크_테스트() {
        Ball player = new Ball(1,1);
        assertThat(computer1.play(player)).isEqualTo(BallStatus.STRIKE);
    }

    @Test
    void Balls_생성_테스트() {
        Balls balls = new Balls(computers);
        List<Ball> balls1 = balls.getBalls();
        assertThat(balls1.get(0).play(computer1)).isEqualTo(BallStatus.STRIKE);
        assertThat(balls1.get(1).play(computer2)).isEqualTo(BallStatus.STRIKE);
        assertThat(balls1.get(2).play(computer3)).isEqualTo(BallStatus.STRIKE);
    }

    @Test
    void 숫자_다대일_낫싱_테스트() {
        Balls balls = new Balls(computers);
        Ball player = new Ball(1, 4);
        assertThat(balls.play(player)).isEqualTo(BallStatus.NOTHING);
    }

    @Test
    void 숫자_다대일_볼_테스트() {
        Balls balls = new Balls(computers);
        Ball player = new Ball(3, 2);
        assertThat(balls.play(player)).isEqualTo(BallStatus.BALL);
    }

    @Test
    void 숫자_다대일_스트라이크_테스트() {
        Balls balls = new Balls(computers);
        Ball player = new Ball(1, 1);
        assertThat(balls.play(player)).isEqualTo(BallStatus.STRIKE);
    }

    @Test
    void 숫자_다대다_테스트() {
        Balls comBalls = new Balls(computers);
        // input - player를 List<Integer>로 넘길까 아니면 Balls 형태로 넘겨야 좋을까
        PlayResult result = comBalls.plays(players);
        // output은 스트라이크와 볼과 관련된 정보를 포함해야 함
        assertThat(result.getBall()).isEqualTo(1);
        assertThat(result.getStrike()).isEqualTo(1);
    }

    /**
     * 3개의 숫자 생성  (1~9) 생성 후 balls 상태로 다른 숫자로 이루어진 3자리체크할땐 총 3번의 비교가 필수.
     * 두번째 생성된 수는 1번비교 3번째 생성된 수는 2번 비교.
     * 최적화는 아니더라도 그냥 자리수마다 전부 balls에서 체크하면 1STRIKE씩 나오고 나머지 2개에서 무조건 NOTHING이 나오면 됨
     * -> 한 자리 무조건 1스트라이크, 나머지 2자리에서 BALL이 나오면 안된다 BALL이 나오는지로 체크하면 될 듯
     * BALLS 만들고 PLAY에 인스로 BALLS의 각각의 BALL 요소를 넣어야 함. 스트림 사용?
     *
     *
     * 주어진 수가 서로 다른 수 라는 전제가 있을때 NNN, 아니면 S하나 N2개, B하나 N2개의 가능성만 있음. N 제외하면 자연스럽게 S or B 나옴
     *B가 나오지 않으면 서로다른수라는 것이 보장되는지 체크. B가 나오지 않고 S하나가 무조건 보장되는 상황이므로 S1개 N2개로 고정됨.
     *
     * 3자리 임의의 리스트만들고 인자로 전달해서 Balls의 인스턴스 생성하고 stream으로 각 ball에 대해 balls.play(ball) 하면서
     * balls의 play가 NOTHING을 제외하고 STRIKE나 BALL 중에서 findFrist하는 로직이므로 나오는 결과가 무조건 STRIKE여야 원하는 조건 만족
     *
     */

    @Test
    void 서로_다른수_검증기_테스트() {
        List<Integer> same1NumList = Arrays.asList(2, 2, 9);
        List<Integer> same2NumList = Arrays.asList(2, 9, 2);
        List<Integer> same3NumList = Arrays.asList(9, 2, 2);
        List<Integer> same4NumList = Arrays.asList(2, 2, 2);
        Balls diffBalls = new Balls(computers);
        Balls same1Balls = new Balls(same1NumList);
        Balls same2Balls = new Balls(same2NumList);
        Balls same3Balls = new Balls(same3NumList);
        Balls same4Balls = new Balls(same4NumList);
        assertThat(Balls.checkNum(diffBalls)).isTrue();
        assertThat(Balls.checkNum(same1Balls)).isFalse();
        assertThat(Balls.checkNum(same2Balls)).isFalse();
        assertThat(Balls.checkNum(same3Balls)).isFalse();
        assertThat(Balls.checkNum(same4Balls)).isFalse();
    }

    @Test
    void 서로다른수_생성_테스트() {
        Balls balls = new Balls();
        assertThat(Balls.checkNum(balls)).isTrue();
    }

    @Test // 이렇게하면 ()를 잘못 처리해서 항상 0+1이여서 1의 값만 나와서 예상한 값을 벗어나는 잘못된 테스트가 만들어졌음 심지어 모든 경우의 수 체크도 안해주는건가 (int) (Math.random()*10)+1로하면 a =10나올때도 있는데 그때만 테스트가 실패함...
    void mathRandomTest() {
       int a = (int) (Math.random()*9)+1;
        System.out.println("a = " + a);
       assertThat(a).isGreaterThan(0);
       assertThat(a).isLessThan(10);
       assertThat(a).isGreaterThanOrEqualTo(1);
       assertThat(a).isLessThanOrEqualTo(9);
    }

    @Test // 값을 출력해서 확인하면 안 좋은데 이런 경우는 어떻게 하지? 자리수 체크정도 가능할텐데 값도 체크해보고 싶다면?
    void createNumTest() {
        List<Integer> number = BaseballNum.createNumber();
        for (int i =0; i < 3; i++) {
            System.out.println("number.get(" + i + ")" + "=" + number.get(i));
        }
    }

    @Test
    void input() {

        List<Integer> computers1 = Arrays.asList(1, 2, 3);
        List<Integer> players1 = Arrays.asList(1, 1, 1);
        List<Integer> players2 = Arrays.asList(1, 2, 3);
        List<Integer> players3 = Arrays.asList(2, 1, 3);
        List<Integer> players4 = Arrays.asList(2, 4, 5);
        List<Integer> players5 = Arrays.asList(4, 2, 5);

        Balls balls = new Balls(computers1);
        PlayResult result = balls.plays(players5);

        result.print();

    }

    @Test
    void 문자_숫자열_변환() {
        String str = "123";
        Converter converter = new Converter(str);
        converter.getIntegerList();
    }
}






