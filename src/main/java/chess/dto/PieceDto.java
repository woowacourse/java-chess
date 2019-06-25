package chess.dto;

import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Type;

public class PieceDto {

    private Point point;
    private Color color;
    private Type type;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
