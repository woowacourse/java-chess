package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Optional;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class Pawn extends Piece {
    public static final int INITIAL_BLACK_PAWN_ROW = 1;
    public static final int INITIAL_WHITE_PAWN_ROW = 6;
    private static final int DEFAULT_PAWN_SCORE = 1;
    private static final int INITIAL_POSSIBLE_DISTANCE_OF_PAWN = 4;
    private static final String PAWN_NAME = "p";

    public Pawn(Color color, Point point) {
        super(PAWN_NAME, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece targetPiece) {
        Direction direction = Direction.findDirection(this.point, targetPiece.point);
        if (isNotMovable(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        return directionOnMovableCondition(targetPiece, direction);
    }

    private Optional<Direction> directionOnMovableCondition(Piece targetPiece, Direction direction) {
        int distance = this.point.calculateDistance(targetPiece.point);
        if (targetPiece.isEmptyPiece() && distance == MOVE_STRAIGHT_ONE_SQUARE) {
            return Optional.of(direction);
        }
        if (!targetPiece.isEmptyPiece() && distance == MOVE_DIAGONAL_ONE_SQUARE) {
            return Optional.of(direction);
        }
        if (targetPiece.isEmptyPiece() && distance == INITIAL_POSSIBLE_DISTANCE_OF_PAWN
                && (isInitialBlackPawn() || isInitialWhitePawn())) {
            return Optional.of(direction);
        }
        throw new IllegalArgumentException("기물이 이동할 수 없는 경로입니다.");
    }

    private boolean isNotMovable(Direction direction) {
        return !Direction.pawnDirection(this.color).contains(direction);
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
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
