package domain;

public enum Rank {
    EIGHT(8), SEVEN(7), SIX(6), FIVE(5),
    FOUR(4), THREE(3), TWO(2), ONE(1);

    public final static int MAX_SIZE = values().length;
    public final static int MIN_SIZE = 1;

    private final int row;

    Rank(int row) {
        this.row = row;
    }

    public static Rank find(int row) {
        Rank[] ranks = values();
        for (var rank : ranks) {
            if (rank.row == row) {
                return rank;
            }
        }
        throw new IllegalArgumentException("체스판을 벗어날 수 없습니다.");
    }

    public int minus(Rank other) {
        return this.row - other.row;
    }

    public int getRow() {
        return row;
    }
}
