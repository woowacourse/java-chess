package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

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
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return pawnMovable(color, displacementX, displacementY);
    }

    private boolean pawnMovable(Color color, int displacementX, int displacementY) {
        if (displacementY == 2 * color.direction() && displacementX == 0) {
            return isNeverDisplaced();
        }
        return displacementY == color.direction() && Math.abs(displacementX) <= 1;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return findLinearRoute(source, target);
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

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isEnPassantAvailable() {
        return moveCount == 1;
    }
}

