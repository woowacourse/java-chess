package chess.domain.pieces;

import java.util.Objects;

public class Name {

    private static final int ROW = 0;
    private static final String PLACE = ".";

    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isLowerCase() {
        return Character.isLowerCase(this.name.charAt(ROW));
    }

    public boolean isUpperCase() {
        return Character.isUpperCase(this.name.charAt(ROW));
    }

    public boolean isPlace() {
        return name.equals(PLACE);
    }

    public boolean isPawn() {
        return name.equals("p") || name.equals("P");
    }

    public boolean isKing() {
        return name.equals("k") || name.equals("K");
    }

    public boolean isKnight() {
        return name.equals("n") || name.equals("N");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
