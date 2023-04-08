package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class NonPawnPiece extends AbstractPiece {

    private final PieceMoveStrategy pieceMoveStrategy;
    private Position position;

    public NonPawnPiece(final Color color, final Position position, final PieceMoveStrategy pieceMoveStrategy) {
        super(color);
        this.position = position;
        this.pieceMoveStrategy = pieceMoveStrategy;
    }

    @Override
    public void move(final Position from, final Position to, final Piece target) {
        validateMovable(from, to, target);

        position = to;
    }

    private void validateMovable(final Position from, final Position to, final Piece target) {
        validateNotSamePosition(from, to);
        validateNotSameColor(target);
        validateMovablePosition(from, to);
    }

    private void validateMovablePosition(final Position from, final Position to) {
        if (!pieceMoveStrategy.isMovable(from, to)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다");
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public PieceType getPieceType() {
        return pieceMoveStrategy.getPieceType();
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}
