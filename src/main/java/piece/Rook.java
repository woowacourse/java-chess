package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class Rook extends Piece {
    private Position position;

    public Rook(final Camp camp) {
        super(camp);
    }

    @Override
    void move(Position targetPosition) {
        this.position = targetPosition;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "r";
        }
        return "R";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Rook rook)) {
            return false;
        }
        return camp == rook.camp && Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }

    public Position getPosition() {
        return position;
    }
}
