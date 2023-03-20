package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.PieceMovementStrategy;

import java.util.List;

public class Piece {

    private final PiecePosition piecePosition;
    private final PieceMovementStrategy pieceMovementStrategy;

    public Piece(final PiecePosition piecePosition, final PieceMovementStrategy pieceMovementStrategy) {
        this.piecePosition = piecePosition;
        this.pieceMovementStrategy = pieceMovementStrategy;
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public List<PiecePosition> waypoints(final PiecePosition destination, final Piece nullablePiece) throws IllegalArgumentException {
        return pieceMovementStrategy.waypoints(path(destination), nullablePiece);
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public Piece move(final PiecePosition destination, final Piece nullablePiece) throws IllegalArgumentException {
        final Path path = path(destination);
        pieceMovementStrategy.validateMove(path, nullablePiece);
        return new Piece(destination, pieceMovementStrategy);
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

    private Path path(final PiecePosition destination) {
        return Path.of(piecePosition, destination);
    }

    public Color color() {
        return pieceMovementStrategy.color();
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    public PieceMovementStrategy pieceMovement() {
        return pieceMovementStrategy;
    }
}
