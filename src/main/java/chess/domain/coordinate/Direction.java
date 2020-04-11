package chess.domain.coordinate;

import java.util.Arrays;

public enum Direction {
    LEFT_UP(-1, 1),
    UP(0, 1),
    RIGHT_UP(1, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_DOWN(-1, -1),
    DOWN(0, -1),
    RIGHT_DOWN(1, -1);

    private final int fileVariation;
    private final int rankVariation;

    Direction(final int fileVariation, final int rankVariation) {
        this.fileVariation = fileVariation;
        this.rankVariation = rankVariation;
    }

    public static Direction findByValue(int fileVariation, int rankVariation) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(fileVariation, rankVariation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("fileVariation : %d, rankVariation : %d, 입력값을 확인하시오.", fileVariation, rankVariation)));
    }

    private boolean isSameDirection(int fileVariation, int rankVariation) {
        return this.fileVariation == fileVariation && this.rankVariation == rankVariation;
    }

    public int getFileVariation() {
        return fileVariation;
    }

    public int getRankVariation() {
        return rankVariation;
    }
}
