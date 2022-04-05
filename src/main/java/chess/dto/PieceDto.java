package chess.dto;

import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;

public class PieceDto {

    private final String name;
    private final String position;
    private final String background;

    public PieceDto(final String name, final Position position, final String background) {
        this.name = name;
        this.position = PositionDto.toDto(position).getPosition();
        this.background = background;
    }

    public static PieceDto toDto(final Piece piece, final Position position) {
        String name = "";
        if (piece.getColor() == Color.BLACK) {
            name = "BLACK-"+ piece.getName();
        }

        if (piece.getColor() == Color.WHITE) {
            name = "WHITE-" + piece.getName();
        }

        return new PieceDto(name, position, background(position));
    }

    public static PieceDto toEmptyDto(final String name, final Position position) {
        return new PieceDto(name, position, background(position));
    }

    private static String background(Position position) {
        final Column column = position.getColumn();
        final Row row = position.getRow();

        if ((column.getValue() + row.getValue()) % 2 == 0) {
            return "black";
        }

        return "white";
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getBackground() {
        return background;
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", background='" + background + '\'' +
                '}';
    }
}
