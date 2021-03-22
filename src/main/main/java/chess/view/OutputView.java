package chess.view;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printInitialBoard(final Board board) {
        int i = 0;
        for (Piece piece : board.getBoard().values()) {
            System.out.print(piece.decideUpperOrLower(piece.getSymbol()));
            if (i++ > 6) {
                i = 0;
                System.out.println();
            }
        }
    }
}
