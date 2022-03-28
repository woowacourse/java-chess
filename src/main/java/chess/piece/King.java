package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;
import java.util.List;

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

    @Override
    public Direction getDirection(final Position from, final Position to) {
        List<Direction> dirs = Direction.getEveryDirections();

        return dirs.stream()
                .filter(direction -> from.canMoveToCurrentDirection(direction, to))
                .findFirst()
                .orElse(null);
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
