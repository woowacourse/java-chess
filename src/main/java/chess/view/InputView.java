package chess.view;

import java.util.List;
import java.util.Scanner;


public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMETER = " ";
    private static final int LIMIT = -1;

    private InputView() {
    }

    public static List<String> askStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        return List.of(scanner.nextLine().split(DELIMETER, LIMIT));
    }

    public static List<String> askNext() {
        return List.of(scanner.nextLine().split(DELIMETER, LIMIT));
    }
}
