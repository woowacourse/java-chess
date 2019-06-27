package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum MoveVector {
    EAST(new Direction(1, 0)),
    WEST(new Direction(-1, 0)),
    NORTH(new Direction(0, 1)),
    SOUTH(new Direction(0, -1)),
    NORTHEAST(new Direction(1, 1)),
    NORTHWEST(new Direction(-1, 1)),
    SOUTHEAST(new Direction(1, -1)),
    SOUTHWEST(new Direction(-1, -1)),
    NIGHTEASTNORTH(new Direction(2, 1)),
    NIGHTEASTSOUTH(new Direction(2, -1)),
    NIGHTWESTNORTH(new Direction(-2, 1)),
    NIGHTWESTSOUTH(new Direction(-2, -1)),
    NIGHTNORTHEAST(new Direction(1, 2)),
    NIGHTNORTHWEST(new Direction(-1, 2)),
    NIGHTSOUTHEAST(new Direction(1, -2)),
    NIGHTSOUTHWEST(new Direction(-1, -2)),
    PAWNFIRSTNORTH(new Direction(0, 2)),
    PAWNFIRSTSOUTH(new Direction(0, -2));

    Direction direction;

    private MoveVector(Direction direction) {
        this.direction = direction;
    }

    public static List<MoveVector> linearDirection() {
        return Arrays.asList(EAST, WEST, NORTH, SOUTH);
    }

    public static List<MoveVector> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<MoveVector> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<MoveVector> knightDirection() {
        return Arrays.asList(NIGHTEASTNORTH, NIGHTEASTSOUTH, NIGHTNORTHEAST, NIGHTNORTHWEST, NIGHTSOUTHEAST, NIGHTSOUTHWEST, NIGHTWESTNORTH, NIGHTWESTSOUTH);
    }

    public static List<MoveVector> whitePawnMoveDirection() {
        return Arrays.asList(NORTH, PAWNFIRSTNORTH);
    }

    public static List<MoveVector> whitePawnAttackDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST);
    }

    public static List<MoveVector> blackPawnMoveDirection() {
        return Arrays.asList(SOUTH, PAWNFIRSTSOUTH);
    }

    public static List<MoveVector> blackPawnAttackDirection() {
        return Arrays.asList(SOUTHEAST, SOUTHWEST);
    }

    public static MoveVector findVector(Direction direction) {
        Optional<MoveVector> vector = Arrays.stream(MoveVector.values())
                .filter(value -> value.direction.equals(direction))
                .findAny();
        if (vector.isPresent()) {
            return vector.get();
        }
        throw new IllegalArgumentException("요청하신 방향으로는 이동할 수 없습니다.");
    }

    public Direction getDirection() {
        return direction;
    }
}
