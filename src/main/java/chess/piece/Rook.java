package chess.piece;

import chess.game.MoveCommand;
import chess.piece.detail.Color;
import chess.piece.detail.Direction;
import chess.piece.detail.Name;
import chess.position.Position;
import java.util.List;

public class Rook extends AbstractPiece {

    private static final int ROOK_SCORE = 5;

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

    @Override
    public Direction getDirection(final Position from, final Position to) {
        List<Direction> dirs = Direction.getVerticalAndHorizontalDirections();

        return dirs.stream()
                .filter(direction -> from.canMoveToCurrentDirection(direction, to))
                .findFirst()
                .orElse(null);
    }

    private boolean canRookMove(final Position from, final Position to) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return columnDistance == 0 || rowDistance == 0;
    }

    @Override
    public double getScore() {
        return ROOK_SCORE;
    }
}
