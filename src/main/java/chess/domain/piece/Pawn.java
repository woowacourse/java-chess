package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "p";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int distanceX = source.calculateDistanceX(target);
        int distanceY = source.calculateDistanceY(target);

        if (color == Color.BLACK) {
            return isBlackMovable(distanceX, distanceY);
        }
        return isWhiteMovable(distanceX, distanceY);
    }

    private boolean isBlackMovable(int distanceX, int distanceY) {
        if (distanceY == 0 && Math.abs(distanceX) == 1) {
            return true;
        }
        if (distanceY == 1 && distanceX == 0) {
            return true;
        }
        return false;
    }

    private boolean isWhiteMovable(int distanceX, int distanceY) {
        if (distanceY == 0 && Math.abs(distanceX) == 1) {
            return true;
        }
        if (distanceY == -1 && distanceX == 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return new ArrayList<>();
    }

    @Override
    public double score() {
        return 1;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}

