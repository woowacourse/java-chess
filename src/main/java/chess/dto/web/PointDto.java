package chess.dto.web;

import chess.domain.board.Point;

public class PointDto {

    final private String x;
    final private String y;

    public PointDto(Point point) {
        this.x = point.xCoordinate();
        this.y = point.yCoordinate();
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
