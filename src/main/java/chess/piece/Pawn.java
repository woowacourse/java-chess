package chess.piece;

import static chess.piece.detail.Color.BLACK;
import static chess.piece.detail.Color.WHITE;

import chess.game.MoveCommand;
import chess.piece.detail.Color;
import chess.piece.detail.Direction;
import chess.piece.detail.Name;
import chess.position.Position;

public class Pawn extends AbstractPiece {

    public static final double REDUCED_PAWN_SCORE = 0.5;
    private static final int WHITE_INITIAL_ROW = 2;
    private static final int BLACK_INITIAL_ROW = 7;

    public Pawn(final Color color) {
        super(Name.PAWN, color);
    }

    @Override
    public boolean canMove(final MoveCommand command) {
        final Position from = command.getFrom();
        final Position to = command.getTo();

        if (color == WHITE) {
            return Direction.getWhitePawnDirections().stream()
                    .anyMatch(direction -> canWhiteMove(from, to, direction));
        }

        if (color == BLACK) {
            return Direction.getBlackPawnDirections().stream()
                    .anyMatch(direction -> canBlackMove(from, to, direction));
        }
        throw new IllegalArgumentException("폰이 이동할 수 있는 경로가 아닙니다.");
    }

    private boolean canWhiteMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.canWhitePawnMove(columnDistance, rowDistance, isPawnInitial(from));
    }

    private boolean canBlackMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);

        return direction.canBlackPawnMove(columnDistance, rowDistance, isPawnInitial(from));
    }

    private boolean isPawnInitial(final Position position) {
        return position.isRowAt(WHITE_INITIAL_ROW) || position.isRowAt(BLACK_INITIAL_ROW);
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
