package chess.domain;

import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;

import java.util.List;
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
            final List<Position> positions,
            final Position inputTargetPosition,
            final Color movablePieceColor
    ) {
        shape.move(MoveRequest.from(
                positions,
                movablePieceColor,
                new PositionDto(position),
                new PositionDto(inputTargetPosition)
        ));

        this.position = inputTargetPosition;
        return position;
    }

    public boolean isSamePosition(final Position findPosition) {
        return position.equals(findPosition);
    }

    public boolean isSamePiece(Piece changedPiece) {
        return this.equals(changedPiece);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) && shape == piece.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, shape);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

}
