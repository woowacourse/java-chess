package chess.piece;

import chess.game.Direction;
import chess.game.Position;
import java.util.Arrays;

public class Bishop extends AbstractPiece {

    private static final double SCORE = 3;

    public Bishop(final Color color) {
        super(Name.BISHOP, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.canDiagonalMove(from, to));
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
