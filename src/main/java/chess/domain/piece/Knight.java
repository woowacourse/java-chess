package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class Knight extends Piece {
    public Knight() {
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
