package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public class King extends Piece {
    public King(final String team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position current, Position destination, ChessBoard chessBoard) {
        return false;
    }

    @Override
    boolean checkPositionRule(Position current, Position destination) {
        return false;
    }
}
