package chess.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEWLINE = System.lineSeparator();
    private static final String CHESS_GAME_INTRO = "체스 게임을 시작합니다." + NEWLINE + "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    private InputView() {
    }

    public static String askGameCommand() {
        System.out.println(CHESS_GAME_INTRO);
        return SCANNER.nextLine()
                .strip();
    }
}
