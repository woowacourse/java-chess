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

    public Rank calculateNextRank(int offset) {
        int nextCoordinate = offset + coordinate;
        return Arrays.stream(values())
                .filter(file -> file.coordinate == nextCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank 좌표입니다."));
    }

    public Difference minus(Rank other) {
        return Difference.from(this.coordinate - other.coordinate);
    }

    public static int length() {
        return values().length;
    }

    public int getCoordinate() {
        return coordinate;
    }
}
