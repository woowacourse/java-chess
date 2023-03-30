package chess.domain.square;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Team {
    private static final Map<Color, Team> CACHE;

    static {
        CACHE = new HashMap<>();
        CACHE.put(Color.BLACK, new Team(Color.BLACK));
        CACHE.put(Color.WHITE, new Team(Color.WHITE));
        CACHE.put(Color.EMPTY, new Team(Color.EMPTY));
    }

    private final Color color;

    private Team(final Color color) {
        this.color = color;
    }

    public static Team from(final Color color) {
        return CACHE.get(color);
    }

    public Team findOpponent() {
        if (this.color == Color.EMPTY) {
            return from(Color.EMPTY);
        }
        if (this.color == Color.WHITE) {
            return from(Color.BLACK);
        }
        return from(Color.WHITE);
    }

    public boolean isEmpty() {
        return this.color == Color.EMPTY;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return color == team.color;
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
