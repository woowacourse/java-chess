package chess.piece;

import chess.game.Direction;
import chess.game.MoveCommand;
import chess.game.Position;

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
