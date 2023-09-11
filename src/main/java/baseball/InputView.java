package baseball;

import java.util.Scanner;

public class InputView {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int next = 1;
        while (next == 1) {
            Balls computers = new Balls();
            next = printLoop(next, computers, sc);
        }
    }

    public static int printLoop(int next, Balls computers, Scanner sc) {
        PlayResult result = new PlayResult();
        while (result.getStrike() != 3) {
            System.out.println("숫자를 입력해주세요.");
            String str = sc.next();

            result = computers.plays(new Converter(str).getIntegerList());
            result.print();
            next = result.getNext();
        }
        return next;
    }

}



