package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    EIGHT(0),
    SEVEN(1),
    SIX(2),
    FIVE(3),
    FOUR(4),
    THREE(5),
    TWO(6),
    ONE(7);


    private final int rowNumber;

    Rank(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static Rank from(int rowNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.rowNumber == rowNumber)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("행 번호가 %d인 랭크를 찾을 수 없습니다", rowNumber)));
    }

    public Rank reverse() {
        return Rank.from(ONE.rowNumber - rowNumber);
    }

    public int calculateDistanceWith(Rank other) {
        return Math.abs(rowNumber - other.rowNumber);
    }

    public boolean isAbove(Rank other) {
        return rowNumber < other.rowNumber;
    }

    public boolean isBelow(Rank other) {
        return rowNumber > other.rowNumber;
    }

    public Rank move(int weight) {
        return from(rowNumber + weight);
    }
}
