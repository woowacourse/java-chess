package chess.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String CHESS_GAME_INTRO = "체스 게임을 시작합니다.";
    private static final String COMMEND_MENU = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    private InputView() {
    }

    public static void printGameIntro() {
        System.out.println(CHESS_GAME_INTRO);
    }

    public static String askGameCommand() {
        System.out.println(COMMEND_MENU);
        return SCANNER.nextLine()
            .strip();
    }
}
