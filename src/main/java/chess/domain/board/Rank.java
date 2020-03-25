package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Rank {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private static Map<Rank, Rank> opposite;

    static {
        opposite = new HashMap<>();
        opposite.put(ONE, EIGHT);
        opposite.put(TWO, SEVEN);
        opposite.put(THREE, SIX);
        opposite.put(FOUR, FIVE);
        opposite.put(FIVE, FOUR);
        opposite.put(SIX, THREE);
        opposite.put(SEVEN, TWO);
        opposite.put(EIGHT, ONE);
    }

    private String name;

    Rank(String name) {
        this.name = name;
    }

    public Rank opposite() {
        return opposite.get(this);
    }

    public Optional<Rank> next() {
        try {
            return Optional.of(values()[ordinal() + 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public Optional<Rank> previous() {
        try {
            return Optional.of(values()[ordinal() - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public String getName() {
        return name;
    }
}
