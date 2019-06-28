package chess.domain;

import chess.domain.pieces.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int INIT_WHITE_PIECE_POINT_Y = 1;
    private static final int INIT_BLACK_PIECE_POINT_Y = 8;
    private static final int INIT_WHITE_PAWN_POINT_Y = 2;
    private static final int INIT_BLACK_PAWN_POINT_Y = 7;
    private static final int START_BLANK_POINT_Y = 3;
    private static final int END_BLANK_POINT_Y = 6;
    private static final int START_BOARD_POINT_X = 1;
    private static final int END_BOARD_POINT_X = 8;

    private Map<Point, Piece> points = new HashMap<>();

    public ChessBoard() {
        initializePiece(Color.WHITE, INIT_WHITE_PIECE_POINT_Y, INIT_WHITE_PAWN_POINT_Y);
        initializePiece(Color.BLACK, INIT_BLACK_PIECE_POINT_Y, INIT_BLACK_PAWN_POINT_Y);
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
        for (int i = START_BOARD_POINT_X; i <= END_BOARD_POINT_X; ++i) {
            points.put(PointFactory.of(i, pawnInitPointY), new Pawn(color));
        }
    }

    private void initializeBlank() {
        for (int y = START_BLANK_POINT_Y; y <= END_BLANK_POINT_Y; ++y) {
            for (int x = START_BOARD_POINT_X; x <= END_BOARD_POINT_X; ++x) {
                points.put(PointFactory.of(x, y), new Blank(Color.NONE));
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
            throw new IllegalArgumentException("해당 위치에는 체스 말이 없습니다!");
        }
        if (!sourcePiece.equalsColor(colorOfTurn)) {
            throw new IllegalArgumentException("현재 턴이 아닙니다!");
        }
        if (targetPiece.equalsColor(colorOfTurn)) {
            throw new IllegalArgumentException("같은 색의 체스 말은 잡을 수 없습니다!");
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
            throw new IllegalArgumentException("체스가 이동하는 중간에 다른 체스 말이 존재합니다!");
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

        return calculateScoreWithoutPawn(color)
                + calculatePawnScore(numberOfPawnInColumn);
    }

    private double calculateScoreWithoutPawn(Color color) {
        return points.values().stream()
                .filter(piece -> piece.equalsColor(color))
                .filter(piece -> !piece.equalsType(Type.PAWN))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePawnScore(List<Integer> numberOfPawnInColumn) {
        return numberOfPawnInColumn.stream()
                .mapToDouble(numberOfPawn -> (numberOfPawn > 1)
                        ? Type.PAWN.getScore() * numberOfPawn * 0.5
                        : Type.PAWN.getScore() * numberOfPawn)
                .sum();
    }

    public Piece getPiece(Point point) {
        return points.get(point);
    }
}
