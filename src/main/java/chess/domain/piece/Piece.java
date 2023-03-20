package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class Piece {

    protected final Color color;
    protected final PiecePosition piecePosition;
    protected final MoveStrategy moveStrategy;

    public Piece(final Color color, final PiecePosition piecePosition, final MoveStrategy moveStrategy) {
        this.color = color;
        this.piecePosition = piecePosition;
        this.moveStrategy = moveStrategy;
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public List<PiecePosition> waypoints(final PiecePosition destination) throws IllegalArgumentException {
        return moveStrategy.waypoints(path(destination));
    }

    protected void validatePath(final Path path) {
        moveStrategy.validatePath(path);
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public Piece move(final PiecePosition destination, final Piece nullablePiece) throws IllegalArgumentException {
        final Path path = path(destination);
        validatePath(path);
        if (nullablePiece != null) {
            validateKill(nullablePiece);
        }
        return new Piece(color, destination, moveStrategy);
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
