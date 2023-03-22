package domain.position;

public enum Rank {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String name;

    Rank(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
