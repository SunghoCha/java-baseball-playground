package baseball;

import java.util.ArrayList;
import java.util.List;

public class Balls {

    private final List<Ball> balls; // 단순한 int 리스트가 아니라 ball 객체 리스트로 변환해서 가지고 있을 수 있다는 생각을 못했었음

    public Balls(List<Integer> computer) {
        List<Ball> balls = mapToBall(computer);
        this.balls = balls;
    }

    public Balls() {
        List<Ball> balls = mapToBall(checkNumRepeat());
        this.balls = balls;
    }

    private static List<Ball> mapToBall(List<Integer> computer) {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < computer.size(); i++) {
            balls.add(new Ball(i+1, computer.get(i)));
        }
        return balls;
    }

    public BallStatus play(Ball player) {
        return balls.stream()
                .map(ball -> ball.play(player))
                .filter(BallStatus::isNotNOTHING)
                .findFirst()
                .orElse(BallStatus.NOTHING);
    }
    /**
     *         주어진 수가 서로 다른 수 라는 전제가 있으므로 NNN, 아니면 S하나 N2개, B하나 N2개의 가능성만 있음. N 제외하면 자연스럽게 S or B 나옴
     *
     *         사실 숫자 일대일비교에서 NOTHING의 의미는 야구게임의 NOTHING의 의미와 다르고 B와 S도 개수가 1개로 정해진게 아니라 처음에 Enum타입
     *         생각해내기 힘들 거 같은데..
     *         N,B,S와 완전히 같은 의미가 아니라해도 일대일비교를 독립적으로 수행할 수 있는 요소로서 정의된다는 것만 전제되면 사실 문제 없을수도?
     *         일반적으로 생각하는 이중for문도 가장 깊은 곳에서 구현되는 것은 독립적인 일대일비교니까 같은 맥락에서 이해 가능
     *         사실 리스트배열을 만들고 for 문을 도는것도 idx와 그에 해당하는 값, 즉 key와 value값으로 특정되는 것이고
     *         상황에 따라 이를 ball처럼 객체로 다루거나 map으로 다루면 될 것 같음
     *
     *
     */

    public List<Ball> getBalls() {
        return balls;
    }

    public PlayResult plays(List<Integer> player) {

        List<Ball> playerBalls = mapToBall(player);
        PlayResult result = new PlayResult();

        playerBalls.stream().forEach(ball -> result.report(this.play(ball)));
        return result;
    }

    /**
     *    createNumber 부터 다시 반복해야 하는데 new Balls가 반복문 안에 있어서 값을 return할 수 없음
     *    그렇다고 메소드의 로컬 변수로 선언하자니 생성할 때 Integer 리스트를 인자로 넣어야해서 createNumber 다음에 나와야 함
     *    DTO같은 거라도 만들어서 같은 객체를 만들 수 있게라도 해볼까 코드가 지저분해지기는 하는데 이게 최선인가?
     *
     */


    private static List<Integer> checkNumRepeat() {
        boolean b;
        List<Integer> IntegerList;
        do {
            IntegerList = BaseballNum.createNumber();
            b = checkNum(new Balls(IntegerList));
        } while (b == false);

        return IntegerList;
    }

    public static boolean checkNum(Balls balls) {
        return balls.getBalls().stream().map(ball -> balls.play(ball)).allMatch(STATUS -> STATUS.isSTRIKE());
    }
}



