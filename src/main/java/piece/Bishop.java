package piece;

import java.util.Objects;
import model.Camp;
import point.Position;

public class Bishop extends Piece {

    private final Camp camp;
    private Position position;


    public Bishop(final Camp camp, Position position) {
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
            return "b";
        }
        return "B";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bishop bishop = (Bishop) o;
        return camp == bishop.camp && Objects.equals(position, bishop.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, position);
    }
}
