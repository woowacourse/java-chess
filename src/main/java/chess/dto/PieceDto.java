package chess.dto;

import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import java.sql.ResultSet;

public class PieceDto {

    private final String name;
    private final String position;
    private final String color;
    private final String background;

    public PieceDto(final String name, final Position position, final String color, final String background) {
        this.name = name;
        this.position = PositionDto.toDto(position).getPosition();
        this.color = color;
        this.background = background;
    }

    public static PieceDto toDto(final Piece piece, final Position position) {
        final String color = piece.getColor().name();
        final String name = color + "-" + piece.getName();

        return new PieceDto(name, position, piece.getColor().name(), background(position));
    }

    public static PieceDto toEmptyDto(final String name, final Position position) {
        return new PieceDto(name, position, name, background(position));
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

    public String getColor() {
        return color;
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
