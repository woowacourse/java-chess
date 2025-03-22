package chess.piece;

import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Bishop extends Piece {

    private final Position position;

    public Bishop(final Team team, final Position position) {
        super(team);
        this.position = position;
    }

    @Override
    public Piece move(final Movement movement) {
        if (!movement.isDiagonal()) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        if (!position.canMove(movement)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        try {
            return new Bishop(team, position.move(movement));
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
    }

    @Override
    public String getDisplay() {
        if (team == Team.A) {
            return "b";
        }
        return "B";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bishop bishop)) {
            return false;
        }
        return Objects.equals(position, bishop.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
