package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class King extends Piece {

    private final Camp camp;
    private Position position;

    public King(final Camp camp, final Position position) {
        this.camp = camp;
        this.position = position;
    }

    @Override
    void move(Position targetPosition) {
        this.position = targetPosition;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "k";
        }
        return "K";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return camp == king.camp && Objects.equals(position, king.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }
}
