package chess.domain;

import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;

import java.util.List;

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

    public boolean isSameShape(Shape shape) {
        return this.shape == shape;
    }

    public Position move(
            final List<Position> positions,
            final Position inputTargetPosition,
            final String movablePieceColor
    ) {
        char file =  inputTargetPosition.getFile();
        int rank = inputTargetPosition.getRank();

        shape.move(MoveRequest.from(
                positions,
                movablePieceColor,
                new PositionDto(position),
                new PositionDto(Position.from(rank, file))
        ));

        Position changedPosition = Position.from(rank, file);
        this.position = changedPosition;
        return changedPosition;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

    public Piece getNewPiece(int file) {
        return new Piece(position.changePosition(file), this.shape);
    }

    public Position getPosition() {
        return position;
    }

    public int getRank() {
        return position.getRank();
    }

    public char getFile() {
        return position.getFile();
    }

    public char getName(String color) {
        return this.shape.getNameByColor(color);
    }

    public boolean isSamePosition(Position findPosition) {
        return position.isSamePosition(findPosition);
    }
}
