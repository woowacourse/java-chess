package chess.view;

import java.util.List;

public class OutputView {

    private static final String GAME_START = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_MOVE_DESCRIPTION = "move source위치 target위치 - 예. move b2 b3";
    private static final String GAME_COMMAND_REQUEST = String.format("> 게임 시작: %s\n> 게임 종료: %s\n> 게임 이동: %s\n",
            Command.START.getAnswer(), Command.END.getAnswer(), GAME_COMMAND_MOVE_DESCRIPTION);
    private static final String ERROR_MESSAGE_FORMAT = "[입력 오류] %s" + System.lineSeparator();

    private OutputView() {
    }

    public static void printGuideMessage() {
        System.out.println(GAME_START);
        System.out.println(GAME_COMMAND_REQUEST);
    }

    public static void printBoard(List<List<String>> board) {
        System.out.println();
        for (List<String> rank : board) {
            printRank(rank);
            System.out.println();
        }
        System.out.println();
    }

    private static void printRank(final List<String> rank) {
        for (String value : rank) {
            System.out.print(value);
        }
    }

    public static void printInputErrorMessage(final Exception exception) {
        System.out.printf(ERROR_MESSAGE_FORMAT, exception.getMessage());
    }
}
