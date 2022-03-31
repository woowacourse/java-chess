package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;

public final class PawnWhiteMoveStrategy extends PawnMoveStrategy {

    private final static List<MovePattern> WHITE_MOVE_PATTERNS = List.of(
            MovePattern.NORTH,
            MovePattern.NE,
            MovePattern.NW,
            MovePattern.PAWN_START_MOVE_OF_WHITE
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());
        return isMovePattern(movePattern, board, source, board.getPiece(target));
    }

    @Override
    protected boolean isMovePattern(final MovePattern movePattern,
                                    final Board board,
                                    final Position source,
                                    final Piece targetPiece) {
        if (!WHITE_MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_OF_WHITE) {
            return isStartMove(board, source, targetPiece, board.getColorOfPiece(source));
        }
        if (movePattern == MovePattern.NORTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.NE || movePattern == MovePattern.NW) {
            return isCatchable(targetPiece, board.getColorOfPiece(source));
        }
        return false;
    }
}
