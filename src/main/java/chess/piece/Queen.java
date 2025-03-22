package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Queen extends Piece {

    private final Position position;

    public Queen(final Team team, final Position position) {
        super(team);
        this.position = position;
    }

    @Override
    public Piece move(final Movement movement) {
        if (!movement.isHorizontal() && !movement.isVertical() && !movement.isDiagonal()) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        if (!position.canMove(movement)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        return new Queen(team, position.move(movement));
    }

    @Override
    public String getDisplay() {
        if (team == Team.A) {
            return "q";
        }
        return "Q";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Queen queen)) {
            return false;
        }
        return Objects.equals(position, queen.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
