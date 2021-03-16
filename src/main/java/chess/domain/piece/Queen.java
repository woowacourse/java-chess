package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public class Queen extends Piece {
    public Queen(final String team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position current, Position destination, ChessBoard chessBoard) {
        return false;
    }
}
