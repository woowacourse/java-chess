package chess.piece;

import chess.Direction;
import chess.MoveCommand;
import chess.Position;

public class Rook extends AbstractPiece {

    public Rook(final Color color) {
        super(Name.ROOK, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        return Direction.getVerticalAndHorizontalDirections().stream()
                .anyMatch(direction -> canRookMove(from, to));
    }

    private boolean canRookMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return columnDistance == 0 || rowDistance == 0;
    }

    @Override
    public double getScore() {
        return 5;
    }
}
