package chess.domain.piece.role;

import chess.domain.position.Position;

public class Rook implements Role {

    private static final int SCORE = 5;

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isStraight(target);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
