package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Rook extends Piece {

    private final Position position;

    public Rook(Team team, final Position position) {
        super(team);
        this.position = position;
    }

    @Override
    public Rook move(final Movement movement) {
        if (!movement.isHorizontal() && !movement.isVertical()) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        if (!position.canMove(movement)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        return new Rook(team, position.move(movement));
    }

    @Override
    public String getDisplay() {
        if (team == Team.A) {
            return "r";
        }
        return "R";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rook rook)) {
            return false;
        }
        return Objects.equals(position, rook.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
