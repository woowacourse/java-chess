package chess.domain.position;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private int index;

    Rank(int index) {
        this.index = index;
    }

    public static int size() {
        return values().length;
    }

    public int getRowNumber() {
        return index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
