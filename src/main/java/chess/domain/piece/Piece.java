package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public abstract class Piece implements Cloneable {

    protected final Color color;
    protected PiecePosition piecePosition;

    public Piece(final Color color, final PiecePosition piecePosition) {
        this.color = color;
        this.piecePosition = piecePosition;
    }

    public List<PiecePosition> waypoints(final PiecePosition destination) {
        final Path path = Path.of(piecePosition, destination);
        validatePath(path);
        return path.waypoints();
    }

    protected abstract void validatePath(final Path path);

    public void move(final PiecePosition destination) {
        final Path path = Path.of(piecePosition, destination);
        validatePath(path);
        this.piecePosition = destination;
    }

    public void moveToKill(final Piece enemy) {
        final Path path = Path.of(piecePosition, enemy.piecePosition);
        validatePath(path);
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
