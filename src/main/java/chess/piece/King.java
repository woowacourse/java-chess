package chess.piece;

import chess.game.Direction;
import chess.game.MoveCommand;
import chess.game.Position;

public class King extends AbstractPiece {

    public King(final Color color) {
        super(Name.KING, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getEveryDirections().stream()
                .anyMatch(direction -> canKingMove(from, to, direction));
    }

    private boolean canKingMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.isEqualTo(columnDistance, rowDistance);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
