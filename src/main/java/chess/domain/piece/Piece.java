package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;

public abstract class Piece implements Cloneable {

    protected final Color color;
    protected PiecePosition piecePosition;

    public Piece(final Color color, final PiecePosition piecePosition) {
        this.color = color;
        this.piecePosition = piecePosition;
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

    public WayPoints wayPointsWithCondition(final PiecePosition destination) {
        validateMovable(new Path(piecePosition, destination));
        return wayPointsWithCondition(new Path(piecePosition, destination));
    }

    protected abstract void validateMovable(final Path path);

    protected abstract WayPoints wayPointsWithCondition(final Path path);

    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Color color() {
        return color;
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    public boolean isAlly(final Piece enemy) {
        return color == enemy.color;
    }

    public void move(final PiecePosition destination) {
        this.piecePosition = destination;
    }

    public void moveAndKill(final Piece enemy) {
        validateKill(enemy);
        this.piecePosition = enemy.piecePosition;
    }

    private void validateKill(final Piece enemy) {
        if (isAlly(enemy)) {
            throw new IllegalArgumentException("아군이 있는 위치로는 이동할 수 없습니다.");
        }
    }

    public abstract boolean isKing();
}
