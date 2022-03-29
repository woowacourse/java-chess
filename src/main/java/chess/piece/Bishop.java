package chess.piece;

import chess.game.MoveCommand;
import chess.position.Position;
import java.util.List;

public class Bishop extends AbstractPiece {

    public Bishop(final Color color) {
        super(Name.BISHOP, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getDiagonalDirections().stream()
                .anyMatch(direction -> canBishopMove(from, to));
    }

    @Override
    public Direction getDirection(final Position from, final Position to) {
        List<Direction> dirs = Direction.getDiagonalDirections();

        return dirs.stream()
                .filter(direction -> from.canMoveToCurrentDirection(direction, to))
                .findFirst()
                .orElse(null);
    }

    private boolean canBishopMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return Math.abs(columnDistance) == Math.abs(rowDistance);
    }

    @Override
    public double getScore() {
        return 3;
    }
}
