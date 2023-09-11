package baseball;

import java.util.Objects;

public class Ball {
    private final int position;
    private final int ballNo;

    public Ball(int position, int ballNo) {
        this.position = position;
        this.ballNo = ballNo;
    }

    public BallStatus play(Ball ball) {
        if (this.equals(ball)) {
            return BallStatus.STRIKE;
        } // 필드 값이 추가될 경우 변경에 취약해지나? 그러면 필드값마다 matchBall처럼 메서드를 일일이 만들어서 해야하나
        // 그런데 애초에 enum을 쓴 것부터가 어느정도 타입이 상수로 정해졌다는 의미이니 변경을 너무 유념할 필요는 없으니 equals 만들어서 쓰는 것도 좋은듯

        if (this.matchBallNo(ball)) {
            return BallStatus.BALL;
        }

        return BallStatus.NOTHING;
    }

    public boolean matchBallNo(Ball ball) {
        return this.ballNo == ball.ballNo;

    }

    public int getBallNo() {
        return ballNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return position == ball.position && ballNo == ball.ballNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, ballNo);
    }
}
