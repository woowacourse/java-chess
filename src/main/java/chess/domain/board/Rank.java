package chess.domain.board;

import chess.domain.piece.type.Direction;
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

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(String rankInput) {
        return findRank(Integer.parseInt(rankInput));
    }

    private static Rank findRank(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value() == value)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 rank값 입니다."));
    }

    public Rank move(Direction direction) {
        int movedY = value + direction.getY();
        return findRank(movedY);
    }

    public boolean isDiff(Rank destinationRank, int diff) {
        return Math.abs(value - destinationRank.value()) == diff;
    }

    public int value() {
        return value;
    }
}
