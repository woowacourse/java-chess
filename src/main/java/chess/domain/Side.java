package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Side {
    private static final Map<Color, Side> CACHE;

    static {
        CACHE = new HashMap<>();
        CACHE.put(Color.BLACK, new Side(Color.BLACK));
        CACHE.put(Color.WHITE, new Side(Color.WHITE));
        CACHE.put(Color.EMPTY, new Side(Color.EMPTY));
    }

    private final Color color;

    private Side(final Color color) {
        this.color = color;
    }

    public static Side from(final Color color) {
        return CACHE.get(color);
    }

    public Side findOpponent() {
        if (this.color == Color.WHITE) {
            return from(Color.BLACK);
        }
        return from(Color.WHITE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side side = (Side) o;
        return color == side.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color.name();
    }

    public Color getColor() {
        return this.color;
    }
}
