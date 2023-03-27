package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n" + "> 게임 시작 : start\n" + "> 게임 종료 : end\n";
    public static final String GAME_COMMAND_MESSAGE = "\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n" + "> 게임 결과 : status\n" + "> 게임 종료 : end\n";

    public static String[] readStartOrEndCommand() {
        System.out.println(GAME_START_MESSAGE);
        return SCANNER.nextLine().split(" ");
    }

    public static String[] readGameCommand() {
        System.out.println(GAME_COMMAND_MESSAGE);
        return SCANNER.nextLine().split(" ");
    }
}
