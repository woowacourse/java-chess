package chess.piece;

import java.util.List;
import java.util.Objects;

import chess.Movement;
import chess.Position;

public class Pawn {

    private static final List<Movement> MOVEMENTS = List.of(Movement.UP, Movement.UP_UP);

    private final Position position;
    private final boolean hasMoveExperience;

    public Pawn(final Position position) {
        this.position = position;
        this.hasMoveExperience = false;
    }

    public Pawn(final Position position, final boolean hasMoveExperience) {
        this.position = position;
        this.hasMoveExperience = hasMoveExperience;
    }

    public Pawn move(final Movement movement) {
        if (!MOVEMENTS.contains(movement) || !position.canMove(movement)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        if (movement.equals(Movement.UP_UP) && hasMoveExperience) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        return new Pawn(position.move(movement), true);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pawn pawn)) {
            return false;
        }
        return Objects.equals(position, pawn.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

}
