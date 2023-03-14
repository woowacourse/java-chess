package view;

import domain.board.Position;
import domain.piece.Piece;
import java.util.Map;

public final class OutputView {
    private static final BoardView boardView = new BoardView();

    public static void printBoard(Map<Position, Piece> board) {
        boardView.printBoard(board);
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
