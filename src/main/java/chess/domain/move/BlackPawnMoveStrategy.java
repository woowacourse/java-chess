package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.List;

public class BlackPawnMoveStrategy extends PawnMoveStrategy{

    private final static List<MovePattern> BLACK_MOVE_PATTERNS = List.of(
            MovePattern.SOUTH,
            MovePattern.SOUTHEAST,
            MovePattern.SOUTHWEST,
            MovePattern.PAWN_START_MOVE_BLACK
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());

        return isRightMovePattern(movePattern, board, source, board.getPiece(target), board.getPieceTeamByPosition(source));
    }

    @Override
    protected boolean isRightMovePattern(final MovePattern movePattern, final Board board, final Position source,
                                       final Piece targetPiece, final Team team) {
        if (!BLACK_MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_BLACK) {
            return isStartMove(board, source, targetPiece, team);
        }
        if (movePattern == MovePattern.SOUTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.SOUTHEAST || movePattern == MovePattern.SOUTHWEST) {
            return isTargetPositionMovable(targetPiece, team);
        }
        return false;
    }
}
