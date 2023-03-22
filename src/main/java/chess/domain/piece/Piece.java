package chess.domain.piece;

import chess.domain.piece.movestrategy.PieceMovementStrategy;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class Piece {

    private Long id;
    private final PiecePosition piecePosition;
    private final PieceMovementStrategy pieceMovementStrategy;

    public Piece(final PiecePosition piecePosition, final PieceMovementStrategy pieceMovementStrategy) {
        this.piecePosition = piecePosition;
        this.pieceMovementStrategy = pieceMovementStrategy;
    }

    public Piece(final Long id, final PiecePosition piecePosition, final PieceMovementStrategy pieceMovementStrategy) {
        this.id = id;
        this.piecePosition = piecePosition;
        this.pieceMovementStrategy = pieceMovementStrategy;
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public List<PiecePosition> waypoints(final PiecePosition destination, final Piece nullablePiece) throws IllegalArgumentException {
        return pieceMovementStrategy.waypoints(piecePosition, destination, nullablePiece);
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public Piece move(final PiecePosition destination, final Piece nullablePiece) throws IllegalArgumentException {
        pieceMovementStrategy.validateMove(piecePosition, destination, nullablePiece);
        return new Piece(id, destination, pieceMovementStrategy);
    }

    public boolean existIn(final PiecePosition piecePosition) {
        return this.piecePosition.equals(piecePosition);
    }

    public boolean isKing() {
        return pieceMovementStrategy.isKing();
    }

    public boolean isPawn() {
        return pieceMovementStrategy.isPawn();
    }

    public double value() {
        return pieceMovementStrategy.judgeValue();
    }

    public Color color() {
        return pieceMovementStrategy.color();
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    public PieceMovementStrategy pieceMovementStrategy() {
        return pieceMovementStrategy;
    }

    public Long id() {
        return id;
    }
}
