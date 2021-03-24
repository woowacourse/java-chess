package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public final class Pawn extends Piece {
    public static final int INITIAL_BLACK_PAWN_ROW = 1;
    public static final int INITIAL_WHITE_PAWN_ROW = 6;
    private static final int DEFAULT_PAWN_SCORE = 1;
    private static final int INITIAL_POSSIBLE_DISTANCE_OF_PAWN = 4;
    private static final String PAWN_NAME = "p";

    public Pawn(Color color, Point point) {
        super(PAWN_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        if (Direction.isNotPawnDirection(direction, this.color)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        if (isNotMovableRoute(targetPiece)) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 경로입니다.");
        }
    }

    private boolean isNotMovableRoute(Piece targetPiece) {
        int distance = this.point.calculateDistance(targetPiece.point);
        if (targetPiece.isEmptyPiece() && distance == MOVE_STRAIGHT_ONE_SQUARE) {
            return false;
        }
        if (!targetPiece.isEmptyPiece() && distance == MOVE_DIAGONAL_ONE_SQUARE) {
            return false;
        }
        if (targetPiece.isEmptyPiece() && distance == INITIAL_POSSIBLE_DISTANCE_OF_PAWN
                && (isInitialBlackPawn() || isInitialWhitePawn())) {
            return false;
        }
        return true;
    }

    private boolean isInitialWhitePawn() {
        return this.color.isSameAs(WHITE) && this.point.isSameRow(INITIAL_WHITE_PAWN_ROW);
    }

    private boolean isInitialBlackPawn() {
        return this.color.isSameAs(BLACK) && this.point.isSameRow(INITIAL_BLACK_PAWN_ROW);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }

    @Override
    public double score() {
        return DEFAULT_PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
