package domain.movement;

import domain.position.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0),

    NE(1, 1),
    SE(1, -1),
    SW(-1, -1),
    NW(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    ENE(2, 1),
    ESE(2, -1),
    WSW(-2, -1),
    WNW(-2, 1),
    SSE(1, -2),
    SSW(-1, -2);

    private final int fileVector;
    private final int rankVector;

    Direction(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public boolean isNorthOrSouth() {
        return this == Direction.N || this == Direction.S;
    }

    public boolean isDiagonalDirection() {
        return List.of(Direction.SE, Direction.SW, Direction.NE, Direction.NW)
                .contains(this);
    }

    public static Direction findDirection(Position source, Position target) {
        ChessVector targetChessVector = target.toVector(source);
        ChessVector unitChessVector = targetChessVector.toUnitVector();
        return Arrays.stream(values())
                .filter(direction -> isSameDirection(direction, unitChessVector))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(Direction direction, ChessVector chessVector) {
        return direction.fileVector == chessVector.getFileVector() && direction.rankVector == chessVector.getRankVector();
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
