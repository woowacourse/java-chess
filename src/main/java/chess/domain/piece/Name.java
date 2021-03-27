package chess.domain.piece;

import java.util.Objects;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class Name {
    private final String name;

    public Name(String name, Color color) {
        this.name = createName(name, color);
    }

    private String createName(String name, Color color) {
        if (color.isSameAs(BLACK)) {
            return name.toUpperCase();
        }
        if (color.isSameAs(WHITE)) {
            return name.toLowerCase();
        }
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
