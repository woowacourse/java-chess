package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean isEmptyPosition(Position position) {
        return !board.containsKey(position);
    }
}
