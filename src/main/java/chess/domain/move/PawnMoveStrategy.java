package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;

public abstract class PawnMoveStrategy extends MoveStrategy {

    private static final int FORWARD_UNIT_BLACK = -1;
    private static final int FORWARD_UNIT_WHITE = 1;

    protected abstract boolean isRightMovePattern(final MovePattern movePattern, final Board board, final Position source,
                                       final Piece targetPiece, final Team team);

    protected boolean isStartMove(final Board board, final Position source, final Piece targetPiece, final Team team) {
        if (!source.isPawnStartPosition(team)) {
            return false;
        }
        return board.getPiece(source.move(NOT_MOVE, findForward(team))).isBlank() && targetPiece.isBlank();
    }

    private int findForward(final Team team) {
        if (team == Team.BLACK) {
            return FORWARD_UNIT_BLACK;
        }
        return FORWARD_UNIT_WHITE;
    }

    @Override
    protected boolean isTargetPositionMovable(final Piece targetPiece, final Team team) {
        return !targetPiece.isBlank() && !targetPiece.isSameTeam(team);
    }
}
