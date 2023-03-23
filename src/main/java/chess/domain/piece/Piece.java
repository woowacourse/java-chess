package chess.domain.piece;

import chess.domain.piece.movestrategy.PieceMovementStrategy;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class Piece {

    private final Color color;
    private final PiecePosition piecePosition;
    private final PieceMovementStrategy pieceMovementStrategy;

    public Piece(final Color color, final PiecePosition piecePosition, final PieceMovementStrategy pieceMovementStrategy) {
        this.color = color;
        this.piecePosition = piecePosition;
        this.pieceMovementStrategy = pieceMovementStrategy;
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public List<PiecePosition> waypoints(final PiecePosition destination, final Piece nullablePiece) {
        validateKillAlly(nullablePiece);
        return pieceMovementStrategy.waypoints(piecePosition, destination, nullablePiece);
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    public Piece move(final PiecePosition destination, final Piece nullablePiece) {
        validateKillAlly(nullablePiece);
        pieceMovementStrategy.validateMove(piecePosition, destination, nullablePiece);
        return new Piece(color, destination, pieceMovementStrategy);
    }

    private void validateKillAlly(final Piece nullablePiece) {
        if (nullablePiece == null) {
            return;
        }
        if (nullablePiece.color == color) {
            throw new IllegalArgumentException("아군을 죽일 수 없습니다.");
        }
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
        return color;
    }

    public PiecePosition piecePosition() {
        return piecePosition;
    }

    public PieceMovementStrategy pieceMovementStrategy() {
        return pieceMovementStrategy;
    }
}
