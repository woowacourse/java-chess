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

    private static final int NEXT_INDEX = 1;
    private static final int PREVIOUS_INDEX = -1;
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

    public Optional<Rank> jump(int index) {
        try {
            return Optional.of(values()[ordinal() + index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public Optional<Rank> next() {
        return jump(NEXT_INDEX);
    }

    public Optional<Rank> previous() {
        return jump(PREVIOUS_INDEX);
    }

    public String getName() {
        return name;
    }
}
