package baseball;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Counter {

    int count = 0;
    int scCount = 0;
    private List<Integer> computer;
    private List<Integer> player;

    public void setComputer(List<Integer> computer) {
        this.computer = computer;
    }

    public void getComputerNum() {
        computer.stream().forEach(System.out::print);
        System.out.println();
    }

    /**
     * 객체 생성할 때 난수 3자리수 셋팅되게 하면 테스트를 못하는데 그러면 setter 써야하나? setter 쓰면 안되는데 그러면
     * 단위 테스트 어떻게 하지?
     *
      */


    public Counter() {

    }



//    public void count(String value) {
//        count = 0; scCount = 0;
//        List<Integer> player = Converter.validateNumber(value).getPlayer();
//        countStrike(player);
//        countBall(player);
//    }

    public void count(String value) {
        count = 0; scCount = 0;
        Converter converter = Converter.validateNumber(value);
        this.player = converter.getPlayer();
        countContainment(computer, player);
        countStrike(computer, player);
        countBall(player);
    }

    public void checkContainment(List<Integer> computer,int num) {

        if ( computer.contains(num)) {
            count++;
        }
    }

    public int countContainment2(List<Integer> computer,List<Integer> player) {

        for (int i = 0; i < computer.size(); i++) {
            checkContainment(computer,player.get(i));
        }
        return count;
    }

    public int countContainment (List<Integer> computer, List<Integer> player) {

        HashSet<Integer> set = new HashSet<>();
        int[] intArrPlayer = player.stream().mapToInt(i -> i).toArray();
        set = makeSet(intArrPlayer, set);
        Integer[] filteredPlayer = set.toArray(new Integer[0]);
        int[] intArrplayer = Arrays.stream(filteredPlayer).mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < intArrplayer.length; i++) {
            checkContainment(computer,intArrplayer[i]);
        }
        return count;
    }

    public void checkStrike(List<Integer> computer,List<Integer> player, int idx) {

        if (computer.get(idx) == player.get(idx)) {
            scCount++;
        }
    }

    public int countStrike(List<Integer> computer,List<Integer> player) {

        for (int i = 0; i < computer.size(); i++) {
            checkStrike(computer,player,i);
        }
        return scCount;
    }

    public int countBall(List<Integer> player) {
        return count - scCount;
    }

    private static int[] makeRandom() {
        int[] intArr = new int[3];
        for (int i = 0; i < 3; i++) {
            intArr[i] = (int) (Math.random() * 10);
        }
        return intArr;
    }

    public List<Integer> makeNumber() {
        int[] intArr;
        HashSet<Integer> set = new HashSet<>();
        do {
            intArr = makeRandom();
            set = makeSet(intArr, set);

        } while (intArr[0] == 0 && set.size() != 3);
        return this.computer = Arrays.stream(intArr).boxed().collect(Collectors.toList());
    }

    public HashSet<Integer> makeSet(int[] intArr, Set set) {
        for (int i = 0; i < intArr.length; i++) {
            set.add(intArr[i]);
        }
        return (HashSet<Integer>) set;
    }

    public int getCount() {
        return count;
    }

    public int getScCount() {
        return scCount;
    }

    /**포함된 개수 체크 함수/ 스트라이크 체크함수 / 총 포함개수 - 스트라이크 개수 = 볼 개수 구하는 함수
     *
     */

}
