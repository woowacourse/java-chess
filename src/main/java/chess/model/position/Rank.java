package chess.model.position;

import chess.model.piece.Side;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

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

    public Rank findNextRank(int offset) {
        int nextCoordinate = offset + coordinate;
        return Arrays.stream(values())
                .filter(file -> file.coordinate == nextCoordinate)
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

    public static int length() {
        return values().length;
    }

    public int getCoordinate() {
        return coordinate;
    }
}
