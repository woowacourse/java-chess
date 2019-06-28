package chess.dto;

import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Type;

public class PieceDto {

    private String point;
    private String color;
    private String type;

    public PieceDto() {
    }

    public PieceDto(Point point) {
        this.point = point.toString();
    }

    public PieceDto(Point point, Piece piece) {
        this.point = point.toString();
        this.color = piece.getColor().toString();
        this.type = piece.getType().toString();
    }

    public PieceDto(Point point, Color color, Type type) {
        this.point = point.toString();
        this.color = color.toString();
        this.type = type.toString();
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
