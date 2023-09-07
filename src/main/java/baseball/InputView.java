package baseball;

import java.util.Scanner;

public class InputView {

   private int strike = 0;
   private int ball = 0;
   Counter counter = new Counter();



      public static void main(String[] args) {

         InputView iv = new InputView();
         iv.counter.makeNumber();
         do {

            System.out.println("숫자를 입력해 주세요 : ");
            Scanner sc = new Scanner(System.in);
            String value = sc.nextLine();
            iv.result(value);

         } while (iv.strike != 3);
         Scanner sc = new Scanner(System.in);
         String value = sc.nextLine();
//         if (value.equals("1")) {
//
//         }


   }
   public void result(String value) {

      counter.count(value);
      strike = counter.getScCount();
      ball = counter.getCount() - strike;
      counter.getComputerNum();
      System.out.println("counter.getCount() = " + counter.getCount());
      System.out.println("strike = " + strike);
      System.out.println("ball = " + ball);
      
      resultPrinter(ball,strike);
   }


      /**
       * 낫싱 0 0
       * 볼  1 0
       * 스트라이크 0 1
       * 볼 스트라이크 1 1
       */
      public void resultPrinter(int ball, int strike) {
         if (strike == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료 게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            //반복 여부 함수 추가;
         }
         if (ball > 0 && strike > 0) {
            System.out.printf("%d볼 %d스트라이크\n",ball, strike);
         }
         if (ball > 0 && strike < 1) {
            System.out.printf("%d볼\n",ball );
         }
         if (ball < 1 && strike > 0) {
            System.out.printf("%d스트라이크\n",strike);
         }
         if (ball < 1 && strike < 1) {
            System.out.printf("%d낫싱\n",strike);
         }

      }

}
