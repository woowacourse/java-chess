package chess.dto.web;

import chess.domain.board.Point;

public class PointDto {

    private final String x;
    private final String y;

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
