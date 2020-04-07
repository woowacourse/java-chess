package domain.pieces;

import domain.point.Point;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBPiecesFactory {

    public static Map<Point, Piece> create(Map<String, Object> piecesDB) {
        Map<Point, Piece> pieces = new LinkedHashMap<>();
        for (String point : piecesDB.keySet()) {
            PiecesType piecesType = PiecesType.findPiece((String) piecesDB.get(point));
            pieces.put(Point.of(point), piecesType.createPiece((String) piecesDB.get(point)));
        }
        return pieces;
    }
}
