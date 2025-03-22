package chess.piece;

import chess.Color;
import chess.position.Movement;
import chess.position.Offset;
import java.util.Optional;
import java.util.Set;

public class 쭈 implements Piece {

    public int moveCount = 0;

    private final Color color;

    private static final Set<Movement> BLACK_INITIAL_MOVEMENTS = Set.of(Movement.DOWN, Movement.DOWN_DOWN);
    private static final Set<Movement> WHITE_INITIAL_MOVEMENTS = Set.of(Movement.UP, Movement.UP_UP);

    private static final Set<Movement> BLACK_MOVEMENTS = Set.of(Movement.DOWN);
    private static final Set<Movement> WHITE_MOVEMENTS = Set.of(Movement.UP);

    private static final Set<Movement> BLACK_KILL_MOVEMENTS = Set.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    private static final Set<Movement> WHITE_KILL_MOVEMENTS = Set.of(Movement.LEFT_UP, Movement.RIGHT_UP);

    public 쭈(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        final Optional<Movement> optionalMovement = Movement.find(offset);
        if (optionalMovement.isEmpty()) {
            return false;
        }
        final Movement movement = optionalMovement.get();

        if (color == Color.BLACK) {
            return canMoveBlack(movement, killFlag);
        }
        return canMoveWhite(movement, killFlag);
    }

    private boolean canMoveBlack(final Movement movement, final boolean killFlag) {
        if (killFlag) {
            return BLACK_KILL_MOVEMENTS.contains(movement);
        }
        if (moveCount == 0) {
            return BLACK_INITIAL_MOVEMENTS.contains(movement);
        }
        return BLACK_MOVEMENTS.contains(movement);
    }

    private boolean canMoveWhite(final Movement movement, final boolean killFlag) {
        if (killFlag) {
            return WHITE_KILL_MOVEMENTS.contains(movement);
        }
        if (moveCount == 0) {
            return WHITE_INITIAL_MOVEMENTS.contains(movement);
        }
        return WHITE_MOVEMENTS.contains(movement);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color + "쭈";
    }
}
