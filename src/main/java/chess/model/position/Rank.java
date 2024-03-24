package chess.model.position;

import chess.model.piece.Side;
import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int coordinate;

    Rank(int coordinate) {
        this.coordinate = coordinate;
    }

    public static Rank from(int coordinate) {
        return Arrays.stream(values())
                .filter(rank -> rank.coordinate == coordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank 좌표입니다."));
    }

    public int minus(Rank other) {
        return this.coordinate - other.coordinate;
    }

    public boolean isPawnInitialRank(Side side) {
        if (side.isWhite()) {
            return TWO.equals(this);
        }
        return SEVEN.equals(this);
    }

    public Rank findNextRank(int offset) {
        int nextCoordinate = offset + coordinate;
        return Arrays.stream(values())
                .filter(file -> file.coordinate == nextCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 Rank 좌표입니다."));
    }
}
