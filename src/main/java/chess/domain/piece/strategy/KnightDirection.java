package chess.domain.piece.strategy;

import java.util.Arrays;

public enum KnightDirection {

    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),
    UP_LEFT_LEFT(-2, 1),
    UP_UP_LEFT(-1, 2);

    private final int fileDifference;
    private final int rankDifference;

    KnightDirection(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }

    public boolean isExist(final int fileDifference, final int rankDifference) {
        return Arrays.stream(values())
                .anyMatch(direction ->
                        direction.fileDifference == fileDifference && direction.rankDifference == rankDifference);
    }
}
