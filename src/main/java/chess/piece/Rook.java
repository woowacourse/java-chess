package chess.piece;

import chess.game.Direction;
import chess.game.Position;
import java.util.Arrays;

public class Rook extends AbstractPiece {

    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(Name.ROOK, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.canHorizontalAndVerticalMove(from, to));
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
