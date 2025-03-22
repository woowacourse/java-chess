package chess.piece;

import chess.Color;
import chess.position.Movement;
import chess.position.Offset;
import java.util.Optional;
import java.util.Set;

public class Knight implements Piece {

    private final Color color;

    private static final Set<Movement> MOVEMENTS = Set.of(
            Movement.LEFT_LEFT_UP, Movement.LEFT_LEFT_DOWN,
            Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT,
            Movement.RIGHT_RIGHT_UP, Movement.RIGHT_RIGHT_DOWN,
            Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT
    );

    public Knight(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
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
    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "말";
    }
}
