package domain.game;

import domain.position.Position;
import java.util.Objects;

public record Square(Position position) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return Objects.equals(position, square.position);
    }
}
