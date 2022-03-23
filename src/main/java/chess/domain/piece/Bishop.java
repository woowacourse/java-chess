package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "b";
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDistanceX(target));
        int distanceY = Math.abs(source.calculateDistanceY(target));

        if (distanceY >= 1 && distanceX == distanceY) {
            return true;
        }
        return false;
    }
}
