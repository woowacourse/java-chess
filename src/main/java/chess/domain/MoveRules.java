package chess.domain;

import java.util.Arrays;
import java.util.List;

import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;

import static chess.domain.Direction.E;
import static chess.domain.Direction.N;
import static chess.domain.Direction.NE;
import static chess.domain.Direction.NW;
import static chess.domain.Direction.S;
import static chess.domain.Direction.SE;
import static chess.domain.Direction.SW;
import static chess.domain.Direction.W;

public class MoveRules {
    private static final List<Direction> PAWN_DIRECTION = Arrays.asList(N, S);
    private static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NE, SE, SW, NW);
    private static final List<Direction> CROSS_DIRECTION = Arrays.asList(N, E, S, W);
    private static final List<Direction> ALL_DIRECTION = Arrays.asList(N, NE, E, SE, S, SW, W, NW);
    private static final int LIMIT_DISTANCE_ONE = 1;
    private static final int LIMIT_DISTANCE_KNIGHT = 3;

    public static boolean pawn(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), LIMIT_DISTANCE_ONE);
        return true;
    }

    private static void validDistance(final int distance, final int limit) {
        if (distance != limit) {
            throw new InvalidDistanceException("움직일 수 있는 거리가 아닙니다.");
        }
    }

    private static void validDirection(final List<Direction> movables, final Direction direction) {
        if (movables.stream().noneMatch(movable -> movable == direction)) {
            throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
        }
    }

    public static boolean king(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(ALL_DIRECTION, direction);
        validDistance(source.distance(target, direction), LIMIT_DISTANCE_ONE);
        return true;
    }

    public static boolean queen(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(ALL_DIRECTION, direction);
        return true;
    }

    public static boolean bishop(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(DIAGONAL_DIRECTION, direction);
        return true;
    }

    public static boolean rook(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(CROSS_DIRECTION, direction);
        return true;
    }


    public static boolean knight(Position source, Position target) {
        Direction direction = source.direction(target);
        validNightDirection(direction);
        validDistance(source.distance(target), LIMIT_DISTANCE_KNIGHT);
        return true;
    }

    private static void validNightDirection(final Direction direction) {
        if (ALL_DIRECTION.stream().anyMatch(movable -> movable == direction)) {
            throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
        }
    }

}
