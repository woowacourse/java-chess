package chess.domain.board;

import java.util.Collections;
import java.util.Map;

import chess.domain.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.kind.Empty;
import chess.domain.piece.kind.Piece;

public class Board {
    public static final int BOARD_SIZE = 8;
    private static final int KINGS_COUNT_TO_PLAY = 2;

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public void movePiece(Point source, Point target, Color currentColor) {
        Piece sourcePiece = selectPiece(source);
        validateSourcePieceNotEmpty(sourcePiece);
        validateSourcePieceColor(currentColor, sourcePiece);

        Piece targetPiece = selectPiece(target);
        validateTargetPieceColor(currentColor, targetPiece);

        if (sourcePiece.isKnight()) {
            replacePiece(source, target);
            return;
        }
        sourcePiece.validateRoute(source, target, targetPiece);
        moveStepByStep(source, target);
    }

    private Piece selectPiece(Point point) {
        return board.get(point);
    }

    private void moveStepByStep(Point source, Point target) {
        Point currentPoint = source;
        Direction direction = source.makeDirection(target);

        boolean isArriveAtTarget = currentPoint.equals(target);
        while (!isArriveAtTarget) {
            currentPoint = currentPoint.createNextPoint(direction);
            isArriveAtTarget = currentPoint.equals(target);
            moveTowardTarget(source, target, isArriveAtTarget);
            checkNextPointPossible(currentPoint, isArriveAtTarget);
        }
    }

    private void moveTowardTarget(Point source, Point target, boolean isArriveAtTarget) {
        if (isArriveAtTarget) {
            replacePiece(source, target);
        }
    }

    private void checkNextPointPossible(Point currentPoint, boolean isArriveAtTarget) {
        if (!isArriveAtTarget) {
            validateNextPoint(currentPoint);
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

    public Score makeScore(Color currentColor) {
        return new Score(this.board, currentColor);
    }

    private void validateSourcePieceNotEmpty(Piece piece) {
        if (piece.isEmptyPiece()) {
            throw new IllegalArgumentException("기물이 존재하지 않습니다.");
        }
    }

    private void validateSourcePieceColor(Color currentColor, Piece sourcePiece) {
        if (sourcePiece.isIncorrectTurn(currentColor)) {
            throw new IllegalArgumentException("기물의 색이 일치하지 않아 움직일 수 없는 기물입니다.");
        }
    }

    private void validateTargetPieceColor(Color currentColor, Piece targetPiece) {
        if (targetPiece.isSameTeam(currentColor)) {
            throw new IllegalArgumentException("도착지에 아군이 존재합니다.");
        }
    }

    private void validateNextPoint(Point point) {
        if (!selectPiece(point).isEmptyPiece()) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
