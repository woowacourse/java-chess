package chess.view;

import chess.Board;
import chess.piece.Piece;
import chess.position.Position;

import java.util.Map;

public class OutputView {
    public static void printBoard(Board board) {
        Map<Position, Piece> pieces = board.getPieces();

    }
}
