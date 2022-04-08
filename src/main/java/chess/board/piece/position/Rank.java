package chess.board.piece.position;

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

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public int absMinus(Rank rank) {
        return Math.abs(index - rank.index);
    }

    public int minus(Rank rank) {
        return this.index - rank.index;
    }

    public static Rank valueOf(int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 값입니다 "));
    }

    public boolean isLessThan(Rank rank) {
        return this.index < rank.index;
    }

    public Rank getNext(int distance) {
        return Rank.valueOf(index + distance);
    }

    public int getIndex() {
        return index;
    }

}
