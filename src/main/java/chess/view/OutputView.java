package chess.view;

import chess.dto.SquareResponse;
import java.util.Arrays;
import java.util.List;

public class OutputView {
    private static final int CRITERIA_Y_POS = 7;
    private static final int BOARD_SIZE = 8;
    private static final String BLANK_SYMBOL = ".";
    private static final String ROW_DELIMITER = "";

    private OutputView() {
    }

    public static void printBoard(List<SquareResponse> responses) {
        String[][] board = initBoard();
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

    private static String[][] initBoard() {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (String[] strings : board) {
            Arrays.fill(strings, BLANK_SYMBOL);
        }
        return board;
    }
}
