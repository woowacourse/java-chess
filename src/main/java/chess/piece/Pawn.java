package chess.piece;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.game.Direction;
import chess.game.Position;

public class Pawn extends AbstractPiece {

    public static double SCORE = 1;
    public static double REDUCED_SCORE = 0.5;

    public Pawn(final Color color) {
        super(Name.PAWN, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        if (isEqualColor(WHITE)) {
            return Direction.getWhitePawnDirections().stream()
                    .anyMatch(direction -> canWhiteMove(from, to, direction));
        }
        if (isEqualColor(BLACK)) {
            return Direction.getBlackPawnDirections().stream()
                    .anyMatch(direction -> canBlackMove(from, to, direction));
        }
        throw new IllegalArgumentException("폰이 이동할 수 있는 경로가 아닙니다.");
    }

    private boolean canWhiteMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return direction.canWhitePawnMove(columnDistance, rowDistance, from.isPawnBeginningRow());
    }

    private boolean canBlackMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return direction.canBlackPawnMove(columnDistance, rowDistance, from.isPawnBeginningRow());
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
