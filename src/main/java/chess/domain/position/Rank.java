package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    ONE(7),
    TWO(6),
    THREE(5),
    FOUR(4),
    FIVE(3),
    SIX(2),
    SEVEN(1),
    EIGHT(0);

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
}
