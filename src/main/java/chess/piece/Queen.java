package chess.piece;

import chess.game.Direction;
import chess.game.Position;
import java.util.Arrays;

public class Queen extends AbstractPiece {

    private static final double SCORE = 9;

    public Queen(final Color color) {
        super(Name.QUEEN, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.canAnyDirectionMove(from, to));
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
