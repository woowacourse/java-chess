package chess.domain.side;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO: Side 클래스 필요성 고민해보기
public class Side {
    private static final Map<Color, Side> CACHE;

    static {
        CACHE = new HashMap<>();
        CACHE.put(Color.NOTHING, new Side(Color.NOTHING));
        CACHE.put(Color.BLACK, new Side(Color.BLACK));
        CACHE.put(Color.WHITE, new Side(Color.WHITE));
    }

    private final Color color;

    private Side(final Color color) {
        this.color = color;
    }

    public static Side from(final Color color) {
        return CACHE.get(color);
    }

    public Side findOpponent() {
        if (this.color == Color.NOTHING) {
            return from(Color.NOTHING);
        }
        if (this.color == Color.WHITE) {
            return from(Color.BLACK);
        }
        return from(Color.WHITE);
    }

    public boolean isOpponent(final Side target) {
        if (this.color == Color.WHITE) {
            return target.color == Color.BLACK;
        }
        if (this.color == Color.BLACK) {
            return target.color == Color.WHITE;
        }
        return false;
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
