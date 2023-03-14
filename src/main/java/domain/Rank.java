public enum Rank {
    EIGHT(0, "8"),
    SEVEN(1, "7"),
    SIX(2, "6"),
    FIVE(3, "5"),
    FOUR(4, "4"),
    THREE(5, "3"),
    TWO(6, "2"),
    ONE(7,"1");

    private final int index;
    private final String value;

    Rank(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
