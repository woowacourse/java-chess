package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Optional;

import static chess.domain.piece.Color.*;

public class Pawn extends Piece {
    public static final int INITIAL_WHITE_PAWN_ROW = 1;
    public static final int INITIAL_BLACK_PAWN_ROW = 6;
    private static final int DEFAULT_PAWN_SCORE = 1;
    private static final int INITIAL_POSSIBLE_DISTANCE_OF_PAWN = 4;
    private static final String PAWN_NAME = "p";

    public Pawn(Color color, Point point) {
        super(PAWN_NAME, color, point);
    }
    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        if (this.color.equals(WHITE) && !direction.equals(Direction.NORTH) && !direction.equals(Direction.NORTH_EAST) && !direction.equals(Direction.NORTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        if (this.color.equals(BLACK) && !direction.equals(Direction.SOUTH) && !direction.equals(Direction.SOUTH_EAST) && !direction.equals(Direction.SOUTH_WEST)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }

        int distance = this.point.calculateDistance(target.point);
        if (distance == MOVE_STRAIGHT_ONE_SQUARE && target instanceof Empty) {
            return Optional.of(direction);
        }
        if (distance == MOVE_DIAGONAL_ONE_SQUARE && !(target instanceof Empty)) {
            return Optional.of(direction);
        }
        if (distance == INITIAL_POSSIBLE_DISTANCE_OF_PAWN && target instanceof Empty &&
                ((this.color.equals(BLACK) && this.point.getRow() == INITIAL_WHITE_PAWN_ROW) ||
                        (this.color.equals(WHITE) && this.point.getRow() == INITIAL_BLACK_PAWN_ROW))) {
            return Optional.of(direction);
        }
        throw new IllegalArgumentException(Piece.IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }

    @Override
    public double score() {
        return DEFAULT_PAWN_SCORE;
    }
}
