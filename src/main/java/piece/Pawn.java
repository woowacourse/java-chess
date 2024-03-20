package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class Pawn extends Piece {

    private Position position;

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {
        this.position = targetPosition;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "p";
        }
        return "P";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pawn pawn = (Pawn) o;
        return camp == pawn.camp && Objects.equals(position, pawn.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }
}
