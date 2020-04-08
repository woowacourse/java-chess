package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public enum Turn {
    WHITE("white"),
    BLACK("black"),
    BLANK("blank");

    private static final Map<String, Turn> ENUM_MAP = new HashMap<>();

    static {
        for (Turn turn : values()) {
            ENUM_MAP.put(turn.name, turn);
        }
    }

    private String name;

    Turn(String name) {
        this.name = name;
    }
    public static Turn of(final String name) {
        return ENUM_MAP.get(name);
    }

    public boolean isNotSame(final Turn turn) {
        return this != turn && this != BLANK && turn != BLANK;
    }
}
