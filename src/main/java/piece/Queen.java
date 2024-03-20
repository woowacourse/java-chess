package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class Queen extends Piece {

    private Position position;

    public Queen(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {
        this.position = targetPosition;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "q";
        }
        return "Q";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Queen queen = (Queen) o;
        return camp == queen.camp && Objects.equals(position, queen.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }
}
