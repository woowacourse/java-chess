package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class Knight extends Piece {

    private final Camp camp;
    private Position position;

    public Knight(final Camp camp, final Position position) {
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
            return "n";
        }
        return "N";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Knight knight = (Knight) o;
        return camp == knight.camp && Objects.equals(position, knight.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }
}
