package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    Map<Point, Unit> points = new HashMap<>();

    public ChessBoard() {
        points.put(PointFactory.of("a1"), new Rook(0));
        points.put(PointFactory.of("b1"), new Knight(0));
        points.put(PointFactory.of("c1"), new Bishop(0));
        points.put(PointFactory.of("d1"), new Queen(0));
        points.put(PointFactory.of("e1"), new King(0));
        points.put(PointFactory.of("f1"), new Bishop(0));
        points.put(PointFactory.of("g1"), new Knight(0));
        points.put(PointFactory.of("h1"), new Rook(0));
//        for (int i = 1; i <= 8; ++i) {
//            points.put(PointFactory.of(i, 2), new Pawn(0));
//        }
//        for (int i = 3; i <= 6; ++i) {
//            for (int j = 1; j <= 8; ++j) {
//                points.put(PointFactory.of(j, i), null);
//            }
//        }
//        for (int i = 1; i <= 8; ++i) {
//            points.put(PointFactory.of(i, 7), new Pawn(1));
//        }
        points.put(PointFactory.of("a8"), new Rook(1));
        points.put(PointFactory.of("b8"), new Knight(1));
        points.put(PointFactory.of("c8"), new Bishop(1));
        points.put(PointFactory.of("d8"), new Queen(1));
        points.put(PointFactory.of("e8"), new King(1));
        points.put(PointFactory.of("f8"), new Bishop(1));
        points.put(PointFactory.of("g8"), new Knight(1));
        points.put(PointFactory.of("h8"), new Rook(1));
    }


    public boolean playOneStep(Integer color, Point source, Point target) {
        Unit currentUnit = points.get(source);
        if (currentUnit == null || !currentUnit.isSameColor(color)) {
            return false;
        }

        if (!currentUnit.isValidMovePattern(source, target)) {
            return false;
        }

        List<Point> path = currentUnit.makePath(source, target);
        for (Point point : path) {
            if (points.get(point) != null)
                return false;
        }
        updateUnitLocation(source, target);
        return true;
    }

    private void updateUnitLocation(Point source, Point target) {
        Unit currentUnit = points.get(source);
        points.put(target, currentUnit);
        points.put(source, null);
    }
}
