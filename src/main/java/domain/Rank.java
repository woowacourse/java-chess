package domain;

public enum Rank {
    EIGHT(8), SEVEN(7), SIX(6), FIVE(5),
    FOUR(4), THREE(3), TWO(2), ONE(1);

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
        throw new IllegalArgumentException("잘못된 위치입니다.");
    }

    public int minus(Rank other) {
        return this.row - other.row;
    }

    public int getRow() {
        return row;
    }
}
