package chess.domain.board;

import java.util.Arrays;

public enum Horizontal {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int weight;

    Horizontal(final String value, final int weight) {
        this.value = value;
        this.weight = weight;
    }

    public static Horizontal find(final String value) {
        return Arrays.stream(Horizontal.values())
                .filter(horizontal -> horizontal.value.equals(value))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Horizontal findFromWeight(int weight) {
        return Arrays.stream(Horizontal.values())
                .filter(horizontal -> horizontal.weight == weight)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSameHorizontal(Position position) {
        return this == position.getHorizontal();
    }

    public String getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
