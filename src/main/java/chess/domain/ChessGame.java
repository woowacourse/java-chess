package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {

    private final Board board;

    public ChessGame(Map<Point, Piece> board) {
        this.board = new Board(board);
    }
}
