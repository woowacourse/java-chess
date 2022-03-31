package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;

public abstract class PawnMoveStrategy implements MoveStrategy {

    private static final int FORWARD_UNIT_BLACK = -1;
    private static final int FORWARD_UNIT_WHITE = 1;

    protected abstract boolean isMovePattern(
            final MovementPattern movementPattern,
            final Board board,
            final Position source,
            final Piece targetPiece
    );

    protected boolean isStartMovable(final Board board,
                                     final Position source,
                                     final Piece targetPiece) {
        if (!source.isPawnStartPosition(board.getTeamOfPiece(source))) {
            return false;
        }
        Position forwardPosition = source.move(Movement.NOT_MOVE, findForwardDirection(board.getTeamOfPiece(source)));
        return board.getPiece(forwardPosition).isBlank() && targetPiece.isBlank();
    }

    private int findForwardDirection(final Team team) {
        if (team == Team.BLACK) {
            return FORWARD_UNIT_BLACK;
        }
        return FORWARD_UNIT_WHITE;
    }

    protected boolean isTargetPieceOppositeTeam(final Piece targetPiece, final Piece sourcePiece) {
        return !targetPiece.isBlank() && targetPiece.getTeam() == sourcePiece.getTeam().oppositeTeam();
    }
}
