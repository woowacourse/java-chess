package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class King extends Piece {
    public King() {
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        return false;
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return false;
    }
}
