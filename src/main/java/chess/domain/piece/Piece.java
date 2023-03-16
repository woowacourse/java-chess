package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Waypoints;

public abstract class Piece implements Cloneable {

    protected final Color color;
    protected PiecePosition piecePosition;

    public Piece(final Color color, final PiecePosition piecePosition) {
        this.color = color;
        this.piecePosition = piecePosition;
    }

    public Waypoints waypoints(final PiecePosition destination) {
        final Path path = Path.of(piecePosition, destination);
        validateMovable(path);
        return Waypoints.from(path.wayPoints());
    }

    protected abstract void validateMovable(final Path path);

    public void move(final PiecePosition destination) {
        this.piecePosition = destination;
    }

    public void moveToKill(final Piece enemy) {
        validateKill(enemy);
        this.piecePosition = enemy.piecePosition;
    }

    protected void validateKill(final Piece enemy) {
        if (isAlly(enemy)) {
            throw new IllegalArgumentException("아군이 있는 위치로는 이동할 수 없습니다.");
        }
    }

    public boolean isAlly(final Piece enemy) {
        return color == enemy.color;
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

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
}
