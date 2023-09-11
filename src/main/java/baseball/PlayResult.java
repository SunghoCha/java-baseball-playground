package baseball;

import java.util.Scanner;

public class PlayResult {

    private int strike = 0;
    private int ball = 0;
    private int next = 1;

    public void report(BallStatus status) {
        if (status.isSTRIKE()) {
            strike++;
        }
        if (status.isBALL()) {
            ball++;
        }
    }

    public int getNext() {
        return next;
    }

    public void isGameEnd() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        next = Integer.parseInt(sc.next());
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public void print() {
        if (strike == 3) {
            System.out.printf("%d스트라이크\n", strike);
            isGameEnd();
        }
        if (ball > 0 && strike > 0) {
            System.out.printf("%d볼 %d스트라이크\n", ball, strike);
        }
        if (ball == 0 && strike > 0 && strike !=3) {
            System.out.printf("%d스트라이크\n", strike);
        }
        if (ball > 0 && strike == 0) {
            System.out.printf("%d볼\n", ball );
        }
        if (ball == 0 && strike == 0) {
            System.out.printf("낫싱\n");
        }
    }
}
