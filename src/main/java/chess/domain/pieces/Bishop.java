package chess.domain.pieces;

import chess.domain.position.Position;

public final class Bishop implements Type {

    @Override
    public boolean isMovable(final Position source, final Position target) {
        return source.isDiagonal(target);
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
        return Symbol.BISHOP.score();
    }

    @Override
    public Symbol symbol() {
        return Symbol.BISHOP;
    }
}
