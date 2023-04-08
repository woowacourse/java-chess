package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class Empty extends AbstractPiece {

    private final Position position;

    public Empty(final Position position) {
        super(Color.EMPTY);
        this.position = position;
    }

    @Override
    public void move(final Position from, final Position to, final Piece target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isFriend(final Piece target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEnemy(final Piece target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.EMPTY;
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}
