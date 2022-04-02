package chess.model.position;

import chess.model.Team;

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
    SE(1, -1),

    NNE(1, 2),
    NNW(-1, 2),
    WWN(-2, 1),
    WWS(-2, -1),
    EEN(2, 1),
    EES(2, -1),
    SSW(-1, -2),
    SSE(1, -2);

    private final int fileGap;
    private final int rankGap;

    Direction(int fileGap, int rankGap) {
        this.fileGap = fileGap;
        this.rankGap = rankGap;
    }

    public static Direction of(Position source, Position target) {
        int fileGap = target.getFileGapDividedByGcd(source);
        int rankGap = target.getRankGapDividedByGcd(source);

        return Arrays.stream(values())
                .filter(direction -> direction.fileGap == fileGap)
                .filter(direction -> direction.rankGap == rankGap)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    public static List<Direction> linear() {
        return List.of(N, S, W, E);
    }

    public static List<Direction> vertical() {
        return List.of(N, S);
    }

    public static List<Direction> horizontal() {
        return List.of(W, E);
    }

    public static List<Direction> diagonal() {
        return List.of(NW, NE, SW, SE);
    }

    public static List<Direction> all() {
        return List.of(N, S, W, E, NW, NE, SW, SE);
    }

    public static List<Direction> knight() {
        return List.of(NNW, NNE, SSW, SSE, WWN, EEN, WWS, EES);
    }

    public static List<Direction> movePawn(Team team) {
        if (team == Team.BLACK) {
            return List.of(S);
        }
        return List.of(N);
    }

    public static List<Direction> attackPawn(Team team) {
        if (team == Team.BLACK) {
            return List.of(SW, SE);
        }
        return List.of(NW, NE);
    }

    public int getFileGap() {
        return fileGap;
    }

    public int getRankGap() {
        return rankGap;
    }
}
