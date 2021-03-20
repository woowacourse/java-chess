package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static String getNewGameCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 종료는 end 명령을 입력하세요.");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return SCANNER.nextLine();
    }

    public static String getUserCommand() {
        return SCANNER.nextLine();
    }
}
