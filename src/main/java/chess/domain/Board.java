package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public static final int BOARD_SIZE = 8;
    private static final int KINGS_COUNT_TO_PLAY = 2;

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
            board.put(Point.of(i, j), PieceType.findPiece(i, j));
        }
    }

    public void movePiece(Point source, Point target, Color currentColor) {
        Piece sourcePiece = selectPiece(source);
        validateSourcePieceNotEmpty(sourcePiece);
        sourcePiece.validateCorrectTurn(currentColor);

        Piece targetPiece = selectPiece(target);
        sourcePiece.validateMovableRoute(source, target, targetPiece);
        moveStepByStep(source, target);
    }

    private Piece selectPiece(Point point) {
        return board.get(point);
    }

    private void moveStepByStep(Point source, Point target) {
        Direction direction = source.findDirection(target);

        Point currentPoint = source;
        boolean isArrivedAtTarget = currentPoint.isSamePoint(target);
        while (!isArrivedAtTarget) {
            // TODO: Knight의 Direction 추가할 것
            Point nextPoint = currentPoint.createNextPoint(direction);
            isArrivedAtTarget = nextPoint.isSamePoint(target);
            movePieceToTargetPoint(source, target, isArrivedAtTarget);
            checkNextPointIfNotArrived(nextPoint, isArrivedAtTarget);
            currentPoint = nextPoint;
        }
    }

    private void movePieceToTargetPoint(Point source, Point target, boolean isArrivedAtTarget) {
        if (isArrivedAtTarget) {
            replacePiece(source, target);
        }
    }

    private void checkNextPointIfNotArrived(Point nextPoint, boolean isArrivedAtTarget) {
        if (!isArrivedAtTarget) {
            validateNextPoint(nextPoint);
        }
    }

    private void replacePiece(Point source, Point target) {
        Piece sourcePiece = board.get(source);

        board.replace(target, sourcePiece);
        board.replace(source, new Empty(Color.NOTHING));
    }

    public boolean hasBothKings() {
        return board.values().stream()
                .filter(Piece::isKing)
                .count() == KINGS_COUNT_TO_PLAY;
    }

    public Score makeScore() {
        return new Score(this.board);
    }

    private void validateSourcePieceNotEmpty(Piece piece) {
        if (piece.isEmptyPiece()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateNextPoint(Point point) {
        if (!selectPiece(point).isEmptyPiece()) {
            throw new IllegalArgumentException("가는 길이 막혀 있어 이동할 수 없습니다.");
        }
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}