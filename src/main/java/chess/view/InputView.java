package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GAME_COMMAND_MESSAGE = "\n> 게임 시작 : start\n" + "> 게임 종료 : end\n" + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n" + "> 게임 결과 : status";

    public static String[] readCommand() {
        System.out.println(GAME_COMMAND_MESSAGE);
        return SCANNER.nextLine().split(" ");
    }
}
