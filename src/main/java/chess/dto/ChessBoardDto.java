package chess.dto;

import chess.domain.Point;
import chess.domain.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardDto {

    private Map<Point, Piece> points;

    public Map<Point, Piece> getPoints() {
        return points;
    }

    public void setPoints(Map<Point, Piece> points) {
        this.points = new HashMap<>(points);
    }
}
