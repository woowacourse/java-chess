package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PawnMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class Pawn extends AbstractPiece {

    private final PawnMoveStrategy pawnMoveStrategy;
    private Position position;

    public Pawn(final Color color, final Position position, final PawnMoveStrategy pawnMoveStrategy) {
        super(color);
        this.position = position;
        this.pawnMoveStrategy = PawnMoveStrategy.from(color);
    }

    @Override
    public void move(final Position from, final Position to, final Piece target) {
        validateMovable(from, to, target);

        position = to;
    }

    private void validateMovable(final Position from, final Position to, final Piece target) {
        validateNotSamePosition(from, to);
        validateNotSameColor(target);
        validateMovablePosition(from, to, target);
    }

    private void validateMovablePosition(final Position from, final Position to, final Piece target) {
        validateMovableToEmpty(from, to, target);
        validateMovableToEnemy(from, to, target);
    }

    private void validateMovableToEmpty(final Position from, final Position to, final Piece target) {
        if (target.isEmpty() && !pawnMoveStrategy.isMovableToEmpty(from, to)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다");
        }
    }

    private void validateMovableToEnemy(final Position from, final Position to, final Piece target) {
        if (isEnemy(target) && !pawnMoveStrategy.isMovableToEnemy(from, to)) {
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
        return pawnMoveStrategy.getPieceType();
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}