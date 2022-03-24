package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "k";
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDistanceX(target));
        int distanceY = Math.abs(source.calculateDistanceY(target));

        if (distanceY == 1 && distanceX == 0) {
            return true;
        }
        if (distanceY == 0 && distanceX == 1) {
            return true;
        }
        if (distanceY == 1 && distanceX == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return new ArrayList<>();
    }

    public double score() {
        return 0;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
