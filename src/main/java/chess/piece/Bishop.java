package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;

public class Bishop extends AbstractPiece {

    public Bishop(final Color color) {
        super(Name.BISHOP, color);
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

        return Math.abs(columnDistance) == Math.abs(rowDistance);
    }

}
