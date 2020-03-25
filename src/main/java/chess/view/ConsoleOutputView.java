package chess.view;

import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    private static final String EMPTY_SQUARE = ".";
    private static final int MAX_BOARD_SIZE = 8;

    @Override
    public void printBoard(final List<String> positions, final Map<String, String> board) {
        for (int i = 1; i <= positions.size(); i++) {
            String piece = board.get(positions.get(i - 1));
            System.out.print(printPiece(piece));
            checkNewLine(i);
        }
    }

    private void checkNewLine(final int i) {
        if (i % MAX_BOARD_SIZE == 0) {
            System.out.println();
        }
    }

    private String printPiece(String piece) {
        if (piece == null) {
            return EMPTY_SQUARE;
        }
        return piece;
    }
}
