package chess.piece;

import chess.game.Direction;
import chess.game.Position;
import java.util.Arrays;

public class King extends AbstractPiece {

    private static final int SCORE = 0;

    public King(final Color color) {
        super(Name.KING, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> canMove(from, to, direction));
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    private boolean canMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return direction.isEqualTo(columnDistance, rowDistance);
    }
}
