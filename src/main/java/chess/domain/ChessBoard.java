package chess.domain;

import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    Map<Point, Piece> points = new HashMap<>();

    public ChessBoard() {
        points.put(PointFactory.of("a1"), new Rook(Color.WHITE));
        points.put(PointFactory.of("b1"), new Knight(Color.WHITE));
        points.put(PointFactory.of("c1"), new Bishop(Color.WHITE));
        points.put(PointFactory.of("d1"), new Queen(Color.WHITE));
        points.put(PointFactory.of("e1"), new King(Color.WHITE));
        points.put(PointFactory.of("f1"), new Bishop(Color.WHITE));
        points.put(PointFactory.of("g1"), new Knight(Color.WHITE));
        points.put(PointFactory.of("h1"), new Rook(Color.WHITE));
        for (int i = 1; i <= 8; ++i) {
            points.put(PointFactory.of(i, 2), new Pawn(Color.WHITE));
        }
        for (int i = 3; i <= 6; ++i) {
            for (int j = 1; j <= 8; ++j) {
                points.put(PointFactory.of(j, i), new Blank(Color.NONE));
            }
        }
        for (int i = 1; i <= 8; ++i) {
            points.put(PointFactory.of(i, 7), new Pawn(Color.BLACK));
        }
        points.put(PointFactory.of("a8"), new Rook(Color.BLACK));
        points.put(PointFactory.of("b8"), new Knight(Color.BLACK));
        points.put(PointFactory.of("c8"), new Bishop(Color.BLACK));
        points.put(PointFactory.of("d8"), new Queen(Color.BLACK));
        points.put(PointFactory.of("e8"), new King(Color.BLACK));
        points.put(PointFactory.of("f8"), new Bishop(Color.BLACK));
        points.put(PointFactory.of("g8"), new Knight(Color.BLACK));
        points.put(PointFactory.of("h8"), new Rook(Color.BLACK));
    }

//    public boolean playOneStep(Color color, Point source, Point target) {
//        Piece currentPiece = points.get(source);
//        if (currentPiece == null || !currentPiece.isSameColor(color)) {
//            return false;
//        }
//
//        if (!currentPiece.isValidDirection(source, target)) {
//            return false;
//        }
//
//        List<Point> path = currentPiece.makePath(source, target);
//        for (Point point : path) {
//            if (points.get(point) != null)
//                return false;
//        }
//        updateUnitLocation(source, target);
//        return true;
//    }

    private void updateUnitLocation(Point source, Point target) {
        Piece currentPiece = points.get(source);
        points.put(target, currentPiece);
        points.put(source, null);
    }
}
