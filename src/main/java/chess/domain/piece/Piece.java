package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class Piece implements Cloneable {

    protected final Color color;
    protected PiecePosition piecePosition;
    protected MoveStrategy moveStrategy;

    public Piece(final Color color, final PiecePosition piecePosition, final MoveStrategy moveStrategy) {
        this.color = color;
        this.piecePosition = piecePosition;
        this.moveStrategy = moveStrategy;
    }

    public List<PiecePosition> waypoints(final PiecePosition destination) {
        validatePath(path(destination));
        return moveStrategy.waypoints(path(destination));
    }

    protected void validatePath(final Path path) {
        if (!moveStrategy.movable(path)) {
            throw new IllegalArgumentException("이동 오류");
        }
    }

    public void move(final PiecePosition destination, final Piece nullablePiece) {
        final Path path = path(destination);
        validatePath(path);
        if (nullablePiece != null) {
            validateKill(nullablePiece);
        }
        this.piecePosition = destination;
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

    protected Path path(final PiecePosition destination) {
        return Path.of(piecePosition, destination);
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

    public MoveStrategy moveStrategy() {
        return moveStrategy;
    }
}
