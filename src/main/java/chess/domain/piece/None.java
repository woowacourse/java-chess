package chess.domain.piece;

import chess.domain.Position;
import java.util.Objects;

public class None {

    private final Position position;

    public None(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        None none = (None) o;
        return Objects.equals(position, none.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "None{" + "position=" + position + '}';
    }
}
