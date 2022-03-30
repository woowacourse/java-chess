package chess.domain.board;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class EmptyPoints {

    private final List<Point> points;

    public EmptyPoints(List<Point> points) {
        this.points = points;
    }

    public static EmptyPoints of(Map<Point, Piece> pointPieces) {
        return new EmptyPoints(pointPieces.keySet()
            .stream()
            .filter(point -> isEmpty(pointPieces.get(point)))
            .collect(Collectors.toUnmodifiableList()));
    }

    private static boolean isEmpty(Piece piece) {
        return piece.isSameType(PieceType.EMPTY);
    }

    public boolean contains(Point point) {
        return this.points.contains(point);
    }

}
