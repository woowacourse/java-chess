package chess.domain;

import chess.domain.pieces.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<Point, Piece> points;

    public ChessBoard() {
        this.points = initialize();
    }

    private Map<Point, Piece> initialize() {
        Map<Point, Piece> points = new HashMap<>();
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
        return points;
    }

    public boolean hasPiece(Point point, Piece piece) {
        Piece pointPiece = points.get(point);
        return pointPiece.equals(piece);
    }

    public void checkSourceAndTarget(Point source, Point target, Color colorOfTurn) {
        Piece sourcePiece = points.get(source);
        Piece targetPiece = points.get(target);

        if (sourcePiece.equalsType(Type.BLANK)) {
            throw new IllegalArgumentException("말이 없다");
        }
        if (!sourcePiece.equalsColor(colorOfTurn)) {
            throw new IllegalArgumentException("색깔이 다르다");
        }
        if (targetPiece.equalsColor(colorOfTurn)) {
            throw new IllegalArgumentException("source와 target 색이 같다.");
        }
    }

    public List<Point> makePath(Point source, Point target) {
        Piece sourcePiece = points.get(source);
        Piece targetPiece = points.get(target);

//        if (targetPiece.equalsType(Type.BLANK)) {
//            return sourcePiece.move(source, target);
//        }
//        return sourcePiece.attack(source, target);

        return sourcePiece.action(source, target, !targetPiece.equalsType(Type.BLANK));
    }

    public void checkPath(List<Point> path) {
        boolean hasBlank = path.stream()
                .anyMatch(point -> !points.get(point).equalsType(Type.BLANK));

        if (hasBlank) {
            throw new IllegalArgumentException("경로에 다른 말 있음.");
        }
    }

    public void updatePieceLocation(Point source, Point target) {
        Piece currentPiece = points.get(source);
        points.put(target, currentPiece);
        points.put(source, new Blank(Color.NONE));
    }

    public double calculateScore(Color color) {
        List<Integer> numberOfPawnInColumn = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);

        points.entrySet().stream()
                .filter(pointPieceEntry -> pointPieceEntry.getValue().equalsColor(color))
                .filter(pointPieceEntry -> pointPieceEntry.getValue().equalsType(Type.PAWN))
                .forEach(entry -> {
                    int columnIndex = entry.getKey().getX() - 1;
                    numberOfPawnInColumn.set(columnIndex, numberOfPawnInColumn.get(columnIndex) + 1);
                });

        double totalScore = points.values().stream()
                .filter(piece -> piece.equalsColor(color))
                .filter(piece -> !piece.equalsType(Type.PAWN))
                .mapToDouble(Piece::getScore)
                .sum();

        totalScore += numberOfPawnInColumn.stream()
                .mapToDouble(numberOfPawn -> (numberOfPawn > 1)
                        ? Type.PAWN.getScore() * numberOfPawn * 0.5
                        : Type.PAWN.getScore() * numberOfPawn)
                .sum();

        return totalScore;
    }

}
