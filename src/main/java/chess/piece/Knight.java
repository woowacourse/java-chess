package chess.piece;

import chess.game.Direction;
import chess.game.Position;

public class Knight extends AbstractPiece {

    private static final double SCORE = 2.5;

    public Knight(final Color color) {
        super(Name.KNIGHT, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Direction.getKnightDirections().stream()
                .anyMatch(direction -> canKnightMove(from, to, direction));
    }

    private boolean canKnightMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.isEqualTo(columnDistance, rowDistance);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
