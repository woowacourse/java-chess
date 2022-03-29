package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public abstract class PawnMoveStrategy extends MoveStrategy {

    private static final int FORWARD_UNIT_BLACK = -1;
    private static final int FORWARD_UNIT_WHITE = 1;

    protected abstract boolean isRightMovePattern(final MovePattern movePattern, final Board board, final Position source,
                                       final Piece targetPiece, final Color color);

    protected boolean isStartMove(final Board board, final Position source, final Piece targetPiece, final Color color) {
        if (!source.isPawnStartPosition(color)) {
            return false;
        }
        return board.getPiece(source.move(NOT_MOVE, findForward(color))).isBlank() && targetPiece.isBlank();
    }

    private int findForward(final Color color) {
        if (color == Color.BLACK) {
            return FORWARD_UNIT_BLACK;
        }
        return FORWARD_UNIT_WHITE;
    }

    @Override
    protected boolean isTargetPositionMovable(final Piece targetPiece, final Color color) {
        return !targetPiece.isBlank() && targetPiece.getColor() == color.oppositeColor();
    }
}
