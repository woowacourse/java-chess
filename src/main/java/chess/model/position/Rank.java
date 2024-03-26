package chess.model.position;

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

    public static int length() {
        return values().length;
    }

    public Rank calculateNextRank(int offset) {
        return Arrays.stream(values())
                .filter(rank -> hasNextCoordinate(rank, offset))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank 좌표입니다."));
    }

    private boolean hasNextCoordinate(Rank rank, int offset) {
        return rank.coordinate == this.coordinate + offset;
    }

    public Difference minus(Rank other) {
        return Difference.from(this.coordinate - other.coordinate);
    }
}
