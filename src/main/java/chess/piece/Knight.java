package chess.piece;

import chess.game.MoveCommand;
import chess.position.Position;
import java.util.List;

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

    @Override
    public Direction getDirection(final Position from, final Position to) {
        List<Direction> dirs = Direction.getKnightDirections();

        return dirs.stream()
                .filter(direction -> from.canMoveToCurrentDirection(direction, to))
                .findFirst()
                .orElse(null);
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
