package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isMovable(Position source, Position target) {
        int distanceX = Math.abs(source.calculateDisplacementXTo(target));
        int distanceY = Math.abs(source.calculateDisplacementYTo(target));

        if (distanceY == 1 && distanceX == 2) {
            return true;
        }
        if (distanceY == 2 && distanceX == 1) {
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
        return 2.5;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
