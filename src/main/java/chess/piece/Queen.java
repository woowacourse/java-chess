package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;

public class Queen extends AbstractPiece {

    public Queen(final Color color) {
        super(Name.QUEEN, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getRookDirections().stream()
                .anyMatch(direction -> canRookMove(from, to));
    }

    private boolean canRookMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        if (columnDistance == 0 || rowDistance == 0) {
            return true;
        }

        return Math.abs(columnDistance) == Math.abs(rowDistance);
    }
}
