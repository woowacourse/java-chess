package chess.domain.pieces;

import chess.domain.position.Position;

public final class Queen implements Type {

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.isStraight(target) || source.isDiagonal(target);
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
        return Symbol.QUEEN.score();
    }

    @Override
    public Symbol symbol() {
        return Symbol.QUEEN;
    }
}
