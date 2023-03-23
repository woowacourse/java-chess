package chess.domain.piece.position;

import java.util.Arrays;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),
    ;

    private final int fileUnit;
    private final int rankUnit;

    Direction(final int fileUnit, final int rankUnit) {
        this.fileUnit = fileUnit;
        this.rankUnit = rankUnit;
    }

    public static Direction byDisplacement(final int fileDisplacement, final int rankDisplacement) {
        return Arrays.stream(Direction.values())
                .filter(it -> match(rankDisplacement, fileDisplacement, it))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 변위에 대응대는 방위가 없습니다."));
    }

    private static boolean match(final int fileDisplacement, final int rankDisplacement, final Direction standard) {
        return standard.rankUnit == unitization(rankDisplacement)
                && standard.fileUnit == unitization(fileDisplacement);
    }

    private static int unitization(final int rankDisplacement) {
        return Integer.compare(rankDisplacement, 0);
    }

    public int fileUnit() {
        return fileUnit;
    }

    public int rankUnit() {
        return rankUnit;
    }
}
