package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class NonPawnPiece implements Piece {

    private final Color color;
    private final PieceMoveStrategy pieceMoveStrategy;
    private Position position;

    public NonPawnPiece(final Color color, final Position position, final PieceMoveStrategy pieceMoveStrategy) {
        this.color = color;
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

    private void validateNotSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("이동하려는 위치가 현재 위치입니다");
        }
    }

    private void validateNotSameColor(final Piece target) {
        if (target.getColor() == color) {
            throw new IllegalArgumentException("같은 색 기물은 잡을 수 없습니다");
        }
    }

    private void validateMovablePosition(final Position from, final Position to) {
        if (!pieceMoveStrategy.isMovable(from, to)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다");
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return color;
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
