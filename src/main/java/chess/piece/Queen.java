package chess.piece;

import chess.game.MoveCommand;
import chess.position.Position;
import java.util.List;

public class Queen extends AbstractPiece {

    public Queen(final Color color) {
        super(Name.QUEEN, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getEveryDirections().stream()
                .anyMatch(direction -> canQueenMove(from, to));
    }

    @Override
    public Direction getDirection(final Position from, final Position to) {
        List<Direction> dirs = Direction.getEveryDirections();

        return dirs.stream()
                .filter(direction -> from.canMoveToCurrentDirection(direction, to))
                .findFirst()
                .orElse(null);
    }

    private boolean canQueenMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        if (columnDistance == 0 || rowDistance == 0) {
            return true;
        }

        return Math.abs(columnDistance) == Math.abs(rowDistance);
    }

    @Override
    public double getScore() {
        return 9;
    }
}
