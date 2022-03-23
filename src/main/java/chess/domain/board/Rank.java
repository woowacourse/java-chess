package chess.domain.board;

public enum Rank {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int value;
    private final String condition;

    Rank(int value, String condition) {
        this.value = value;
        this.condition = condition;
    }
}
