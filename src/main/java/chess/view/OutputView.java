package chess.view;

import chess.dto.SquareResponse;
import java.util.List;

public class OutputView {
    private static final int CRITERIA_Y_POS = 7;
    private static final int BOARD_SIZE = 8;
    private static final String ROW_DELIMITER = "";
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String COMMAND_INPUT_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(COMMAND_INPUT_MESSAGE);
    }

    public static void printBoard(List<SquareResponse> responses) {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (SquareResponse response : responses) {
            int x = response.getX();
            int y = convertYPos(response.getY());
            String symbol = response.getSymbol();
            board[y][x] = symbol;
        }
        for (String[] row : board) {
            System.out.println(String.join(ROW_DELIMITER, row));
        }
    }

    private static int convertYPos(int yPos) {
        return CRITERIA_Y_POS - yPos;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
