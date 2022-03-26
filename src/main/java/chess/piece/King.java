package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;

public class King extends AbstractPiece {

    public King(final Color color) {
        super(Name.KING, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getKingDirections().stream()
                .anyMatch(direction -> canKingMove(from, to, direction));
    }

    private boolean canKingMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.isEqualTo(columnDistance, rowDistance);
    }
}
