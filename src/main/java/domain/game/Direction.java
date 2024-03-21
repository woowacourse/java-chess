package domain.game;

import domain.position.Position;
import java.util.Arrays;

public enum Direction {

    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    // KNIGHT DIRECTION
    UP_RIGHT(1, 2),
    UP_LEFT(-1, 2),
    RIGHT_UP(2, 1),
    RIGHT_DOWN(2, -1),
    LEFT_DOWN(-2, -1),
    LEFT_UP(-2, 1),
    DOWN_RIGHT(1, -2),
    DOWN_LEFT(-1, -2);

    private final int fileStepSize;
    private final int rankStepSize;

    Direction(final int fileStepSize, final int rankStepSize) {
        this.fileStepSize = fileStepSize;
        this.rankStepSize = rankStepSize;
    }


    public static Direction findDirection(Position sourcePosition, Position targetPosition) {
        DirectionVector directionVector = targetPosition.subtract(sourcePosition);
        return Arrays.stream(values())
                .filter(direction -> isSameDirection(direction, directionVector))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    // TODO : 변수명 재정의
    private static boolean isSameDirection(final Direction direction, final DirectionVector directionVector) {
        for (int step = 1; step <= 8; step++) {
            if (canReach(findDistance(direction.fileStepSize, step), directionVector.fileVector()) && canReach(findDistance(direction.rankStepSize, step), directionVector.rankVector())){
                return true;
            }
        }
        return false;
    }

    private static int findDistance(int unitVector, int step) {
        return unitVector * step;
    }

    private static boolean canReach(int step, int unitVector) {
        return step == unitVector;
    }
    

    public int getFileStepSize() {
        return fileStepSize;
    }

    public int getRankStepSize() {
        return rankStepSize;
    }
}
