package chess.model.domain.piece;

import java.util.Arrays;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    UP_RIGHT_L(1, 2),
    UP_LEFT_L(-1, 2),
    LEFT_UP_L(-2, 1),
    LEFT_DOWN_L(-2, -1),
    DOWN_LEFT_L(-1, -2),
    DOWN_RIGHT_L(1, -2),
    RIGHT_DOWN_L(2, -1),
    RIGHT_UP_L(2, 1);

    private static final int REVERSE_NUMBER = -1;

    private final int fileAddAmount;
    private final int rankAddAmount;

    Direction(int fileAddAmount, int rankAddAmount) {
        this.fileAddAmount = fileAddAmount;
        this.rankAddAmount = rankAddAmount;
    }

    public int getMultiplyFileAddAmount(int value) {
        return fileAddAmount * value;
    }

    public int getMultiplyRankAddAmount(int value) {
        return rankAddAmount * value;
    }

    public Direction reverse() {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.fileAddAmount == this.fileAddAmount * REVERSE_NUMBER)
            .filter(direction -> direction.rankAddAmount == this.rankAddAmount * REVERSE_NUMBER)
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
    }
}
