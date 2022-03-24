package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private boolean isNeverDisplaced;

    public Pawn(Color color) {
        super(color);
        isNeverDisplaced = false;
    }

    @Override
    protected String baseSignature() {
        return "p";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int displacementX = source.calculateDisplacementX(target);
        int displacementY = source.calculateDisplacementY(target);

        if (color == Color.BLACK) {
            return isBlackMovable(displacementX, displacementY);
        }
        return isWhiteMovable(displacementX, displacementY);
    }

    private boolean isBlackMovable(int displacementX, int displacementY) {
        if (displacementY == 0 && Math.abs(displacementX) == 1) {
            return true;
        }
        if (displacementY == 1 && displacementX == 0) {
            return true;
        }
        if (displacementY == 2 && displacementX == 0){
            return isNeverDisplaced;
        }
        return false;
    }

    private boolean isWhiteMovable(int displacementX, int displacementY) {
        if (displacementY == 0 && Math.abs(displacementX) == 1) {
            return true;
        }
        if (displacementY == -1 && displacementX == 0) {
            return true;
        }
        if (displacementY == -2 && displacementX == 0){
            return isNeverDisplaced;
        }
        return false;
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        isNeverDisplaced = true;
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
}

