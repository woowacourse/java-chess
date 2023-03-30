package chess.domain;

import chess.domain.strategy.PieceStrategy;
import java.util.Objects;

public class Piece {

    private Position position;
    private final Shape shape;

    private Piece(final Position position, final Shape shape) {
        this.position = position;
        this.shape = shape;
    }

    public static Piece from(final int rank, final char file, final Shape shape) {
        return new Piece(Position.from(rank, file), shape);
    }

    public boolean isSameShape(final Shape shape) {
        return this.shape == shape;
    }

    public Position move(
            final Position targetPosition,
            final Color sourcePieceColor,
            final boolean doesTargetPositionHavePiece
    ) {
        PieceStrategy pieceStrategy = shape.getPieceStrategy();
        pieceStrategy.validateDirection(position, targetPosition, sourcePieceColor, doesTargetPositionHavePiece);
        this.position = targetPosition;
        return position;
    }
    public boolean isSamePosition(final Position findPosition) {
        return position.equals(findPosition);
    }

    public Piece getNewPiece(final int file) {
        return new Piece(position.changePosition(file), this.shape);
    }

    public Position getPosition() {
        return position;
    }

    public int getRank() {
        return position.getRankValue();
    }

    public char getFile() {
        return position.getFileValue();
    }

    public double getScore() {
        return shape.getScore();
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

}
