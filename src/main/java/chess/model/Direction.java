package chess.model;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0),
    NW(-1, 1),
    NE(1, 1),
    SW(-1, -1),
    SE(1, -1);

    private final int fileGap;
    private final int rankGap;

    Direction(int fileGap, int rankGap) {
        this.fileGap = fileGap;
        this.rankGap = rankGap;
    }

    public static Direction of(Position source, Position target) {
        int fileGap = target.getFile().getGap(source.getFile());
        int rankGap = target.getRank().getGap(source.getRank());

        return Arrays.stream(values())
                .filter(direction -> direction.fileGap == fileGap)
                .filter(direction -> direction.rankGap == rankGap)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    public static List<Direction> linear() {
        return List.of(N, S, W, E);
    }

    public static List<Direction> diagonal() {
        return List.of(NW, NE, SW, SE);
    }

    public static List<Direction> all() {
        return List.of(N, S, W, E, NW, NE, SW, SE);
    }

    public int getFileGap() {
        return fileGap;
    }

    public int getRankGap() {
        return rankGap;
    }
}
