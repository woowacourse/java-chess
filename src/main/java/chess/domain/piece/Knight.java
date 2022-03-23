package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "n";
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDistanceX(target));
        int distanceY = Math.abs(source.calculateDistanceY(target));

        if (distanceY == 1 && distanceX == 2) {
            return true;
        }
        if (distanceY == 2 && distanceX == 1) {
            return true;
        }
        return false;
    }
}
