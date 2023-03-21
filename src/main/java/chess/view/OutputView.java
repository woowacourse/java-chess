package chess.view;

import chess.dto.SquareResponse;
import java.util.List;

public class OutputView {
    private static final int CRITERIA_Y_POS = 7;
    private static final int BOARD_SIZE = 8;
    private static final String ROW_DELIMITER = "";
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String COMMAND_START_MESSAGE = "> 게임 시작 : start";
    private static final String COMMAND_END_MESSAGE = "> 게임 종료 : end";
    private static final String COMMAND_MOVE_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(COMMAND_START_MESSAGE);
        System.out.println(COMMAND_END_MESSAGE);
        System.out.println(COMMAND_MOVE_MESSAGE);
    }

    public static void printBoard(List<SquareResponse> responses) {
        String[][] board = createBoard(responses);
        for (String[] row : board) {
            System.out.println(String.join(ROW_DELIMITER, row));
        }
        System.out.println();
    }

    private static String[][] createBoard(List<SquareResponse> responses) {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (SquareResponse response : responses) {
            int x = response.getX();
            int y = convertYPos(response.getY());
            board[y][x] = response.getSymbol();
        }
        return board;
    }

    private static int convertYPos(int yPos) {
        return CRITERIA_Y_POS - yPos;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
