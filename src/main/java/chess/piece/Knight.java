package chess.piece;

import chess.position.Movement;
import chess.position.Offset;
import java.util.Optional;
import java.util.Set;

public class Knight implements Piece {

    private static final Set<Movement> MOVEMENTS = Set.of(
            Movement.LEFT_UP, Movement.RIGHT_UP,
            Movement.LEFT_DOWN, Movement.RIGHT_DOWN
    );

    @Override
    public boolean canMove(final Offset offset) {
        Optional<Movement> optionalMovement = Movement.find(offset);
        if (optionalMovement.isEmpty()) {
            return false;
        }
        if (MOVEMENTS.contains(optionalMovement.get())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "말";
    }
}
