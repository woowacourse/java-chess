package chess.domain.movepattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum NormalMovePattern implements MovePattern{

    LEFT_TOP(-1, 1),
    RIGHT_TOP(1, 1),
    LEFT_BOTTOM(-1, -1),
    RIGHT_BOTTOM(1, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int fileVector;
    private final int rankVector;

    NormalMovePattern(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public static List<MovePattern> bishopMovePattern() {
        return List.of(LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM);
    }

    public static List<MovePattern> blackPawnMovePattern() {
        return List.of(DOWN, LEFT_BOTTOM, RIGHT_BOTTOM);
    }

    public static List<MovePattern> whitePawnMovePattern() {
        return List.of(UP, LEFT_TOP, RIGHT_TOP);
    }

    public static List<MovePattern> kingMovePattern() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }

    public static List<MovePattern> queenMovePattern() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }

    public static List<MovePattern> rookMovePattern() {
        return List.of(LEFT, RIGHT, UP, DOWN);
    }

    @Override
    public int fileVector() {
        return fileVector;
    }

    @Override
    public int rankVector() {
        return rankVector;
    }
}
