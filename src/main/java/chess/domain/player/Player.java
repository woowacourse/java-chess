package chess.domain.player;

import java.util.Objects;

public class Player {

    private final int id;
    private final String name;

    private Player(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Player of(final int id, final String name) {
        return new Player(id, name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
