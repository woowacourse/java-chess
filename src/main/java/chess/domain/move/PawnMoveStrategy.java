package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public abstract class PawnMoveStrategy extends MoveStrategy {

    private static final int FORWARD_UNIT_BLACK = -1;
    private static final int FORWARD_UNIT_WHITE = 1;

    protected boolean isStartMove(final Board board, final Position source, final Piece targetPiece, final Color color) {
        if (!source.isPawnStartPosition(color)) {
            return false;
        }
        Position forwardPosition = source.move(Distance.NOT_MOVE, findForwardDirection(color));
        return board.getPiece(forwardPosition).isBlank() && targetPiece.isBlank();
    }

    protected int findForwardDirection(final Color color) {
        if (color == Color.BLACK) {
            return FORWARD_UNIT_BLACK;
        }
        return FORWARD_UNIT_WHITE;
    }


    protected boolean isCatchable(final Piece targetPiece, final Color color) {
        return !targetPiece.isBlank() && targetPiece.getColor() == color.oppositeColor();
    }
}
