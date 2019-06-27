package chess.domain;

import chess.domain.pieces.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<Point, Piece> points = new HashMap<>();

    public ChessBoard() {
        initializePiece(Color.WHITE, 1, 2);
        initializePiece(Color.BLACK, 8, 7);
        initializeBlank();
    }

    public ChessBoard(Map<Point, Piece> points) {
        this.points = new HashMap<>(points);
    }

    private void initializePiece(Color color, Integer pieceInitPointY, Integer pawnInitPointY) {
        points.put(PointFactory.of("a" + pieceInitPointY), new Rook(color));
        points.put(PointFactory.of("b" + pieceInitPointY), new Knight(color));
        points.put(PointFactory.of("c" + pieceInitPointY), new Bishop(color));
        points.put(PointFactory.of("d" + pieceInitPointY), new Queen(color));
        points.put(PointFactory.of("e" + pieceInitPointY), new King(color));
        points.put(PointFactory.of("f" + pieceInitPointY), new Bishop(color));
        points.put(PointFactory.of("g" + pieceInitPointY), new Knight(color));
        points.put(PointFactory.of("h" + pieceInitPointY), new Rook(color));
        for (int i = 1; i <= 8; ++i) {
            points.put(PointFactory.of(i, pawnInitPointY), new Pawn(color));
        }
    }

    private void initializeBlank() {
        for (int i = 3; i <= 6; ++i) {
            for (int j = 1; j <= 8; ++j) {
                points.put(PointFactory.of(j, i), new Blank(Color.NONE));
            }
        }
    }

    public Map<Point, Piece> getPoints() {
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
                .filter(point -> point.getValue().equalsColor(color))
                .filter(point -> point.getValue().equalsType(Type.PAWN))
                .forEach(point -> {
                    int columnIndex = point.getKey().getX() - 1;
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

    public Piece getPiece(Point point) {
        return points.get(point);
    }
}
