package chess.piece;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.game.Direction;
import chess.game.MoveCommand;
import chess.game.Position;

public class Pawn extends AbstractPiece {

    public static double REDUCED_PAWN_SCORE = 0.5;

    public Pawn(final Color color) {
        super(Name.PAWN, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        if (getColor() == WHITE) {
            return Direction.getWhitePawnDirections().stream()
                    .anyMatch(direction -> canWhiteMove(from, to, direction));
        }

        if (getColor() == BLACK) {
            return Direction.getBlackPawnDirections().stream()
                    .anyMatch(direction -> canBlackMove(from, to, direction));
        }
        throw new IllegalArgumentException("폰이 이동할 수 있는 경로가 아닙니다.");
    }

    private boolean canWhiteMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.canWhitePawnMove(columnDistance, rowDistance, from.isPawnInitial());
    }

    private boolean canBlackMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.canBlackPawnMove(columnDistance, rowDistance, from.isPawnInitial());
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return 1;
    }
}
