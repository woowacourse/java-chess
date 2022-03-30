package chess;

import java.util.Scanner;

public class InputView {

    private static final String ANNOUNCE_START_FORMAT = "> ";
    private static final String GAME_START_EXAMPLE = "게임 시작 : start";
    private static final String NEW_LINE = "\n";
    private static final String GAME_END_EXAMPLE = "게임 종료 : end";

    private static final Scanner scanner = new Scanner(System.in);

    public static String[] inputStartOrEndGame() {
        System.out.println(ANNOUNCE_START_FORMAT + GAME_START_EXAMPLE + NEW_LINE
                + ANNOUNCE_START_FORMAT + GAME_END_EXAMPLE + NEW_LINE);
        return scanner.nextLine().split(" ");
    }
}
