package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String CHESS_GAME_INTRO = "> 체스 게임을 시작합니다.";
    private static final String COMMAND_MENU = "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String COMMAND_DELIMITER = " ";

    private InputView() {
    }

    public static void printGameIntro() {
        System.out.println(CHESS_GAME_INTRO);
    }

    public static List<String> askGameCommands() {
        System.out.println(COMMAND_MENU);
        String[] commands = SCANNER.nextLine()
                .strip()
                .split(COMMAND_DELIMITER);
        return Arrays.stream(commands)
                .toList();
    }
}
