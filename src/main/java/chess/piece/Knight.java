package chess.piece;

import chess.game.Direction;
import chess.game.MoveCommand;
import chess.game.Position;

public class Knight extends AbstractPiece {

    public Knight(final Color color) {
        super(Name.KNIGHT, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

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
        return 2.5;
    }
}
