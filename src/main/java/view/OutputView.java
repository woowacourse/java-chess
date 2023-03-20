package view;

import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public final class OutputView {
    private static final BoardView boardView = new BoardView();

    public static void printBoard(final Map<Position, Piece> board) {
        boardView.printBoard(board);
    }

    public static void printError(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
