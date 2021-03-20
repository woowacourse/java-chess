package chess.domain;

import chess.domain.piece.*;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static final int PAWN_COUNT_THRESHOLD_TO_HALF_SCORE = 2;
    private static final int KINGS_COUNT_TO_PLAY = 2;
    private static final double OVERLAPPED_PAWN_SCORE = 0.5;

    private final Map<Point, Piece> board = new HashMap<>();

    public Board() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            initializeColumn(i);
        }
    }

    private void initializeColumn(int i) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board.put(Point.valueOf(i, j), PieceType.findPiece(i, j));
        }
    }

    public void movePiece(Point source, Point target, Color currentColor) {
        Piece sourcePiece = selectPiece(source);
        validateSourcePieceNotEmpty(sourcePiece);
        validateSourcePieceColor(currentColor, sourcePiece);

        Piece targetPiece = selectPiece(target);
        validateTargetPieceColor(currentColor, targetPiece);

        moveStepByStep(source, target, sourcePiece, targetPiece);
    }


    private Piece selectPiece(Point point) {
        return board.get(point);
    }

    private void moveStepByStep(Point source, Point target, Piece sourcePiece, Piece targetPiece) {
        Point currentPoint = source;
        Direction direction = sourcePiece.direction(targetPiece).orElse(null);
        boolean isArriveAtTarget = currentPoint.equals(target);
        while (!isArriveAtTarget) {
            Piece temp = selectPiece(currentPoint);
            currentPoint = temp.moveOneStep(target, direction);
            isArriveAtTarget = currentPoint.equals(target);
            moveTowardTarget(source, target, sourcePiece, isArriveAtTarget);
            checkNextPointPossible(currentPoint, isArriveAtTarget);
        }
    }

    private void checkNextPointPossible(Point currentPoint, boolean flag) {
        if (!flag) {
            validateNextPoint(currentPoint);
        }
    }

    private void moveTowardTarget(Point source, Point target, Piece sourcePiece, boolean flag) {
        if (flag) {
            sourcePiece.movePoint(target);
            replacePiece(source, target);
        }
    }

    public boolean hasBothKings() {
        return board.values().stream()
                .filter(Piece::isKing)
                .count() == KINGS_COUNT_TO_PLAY;
    }

    private void validateSourcePieceNotEmpty(Piece piece) {
        if (piece.isEmptyPiece()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateSourcePieceColor(Color currentColor, Piece sourcePiece) {
        if (sourcePiece.isNotSameColor(currentColor)) {
            throw new IllegalArgumentException("기물의 색이 일치하지 않아 움직일 수 없는 기물입니다.");
        }
    }

    private void validateTargetPieceColor(Color currentColor, Piece targetPiece) {
        if (targetPiece.isSameColor(currentColor)) {
            throw new IllegalArgumentException("도착지에 아군이 존재합니다.");
        }
    }

    private void validateNextPoint(Point point) {
        if (!(selectPiece(point).isEmptyPiece())) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void replacePiece(Point source, Point target) {
        Piece sourcePiece = board.get(source);

        board.replace(target, sourcePiece);
        board.replace(source, new Empty(Color.NOTHING, source));
    }

    public double addScore(Color color) {
        double totalScore = board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .map(Piece::score)
                .reduce((double) 0, Double::sum);

        return totalScore - calculatePawnScore(color);
    }

    private double calculatePawnScore(Color color) {
        int pawnCountInColumn = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            pawnCountInColumn += addColumnPawnCount(color, i);
        }
        return pawnCountInColumn * OVERLAPPED_PAWN_SCORE;
    }

    private int addColumnPawnCount(Color color, int column) {
        long columnPawnCount = board.keySet().stream()
                .filter(point -> point.getColumn() == column)
                .map(board::get)
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .count();
        if(columnPawnCount >= PAWN_COUNT_THRESHOLD_TO_HALF_SCORE) {
            return (int) columnPawnCount;
        }
        return 0;
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }
}
