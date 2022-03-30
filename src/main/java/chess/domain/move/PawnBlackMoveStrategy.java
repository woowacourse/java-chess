package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public final class PawnBlackMoveStrategy extends PawnMoveStrategy {

    private final static List<MovePattern> BLACK_MOVE_PATTERNS = List.of(
            MovePattern.SOUTH,
            MovePattern.SE,
            MovePattern.SW,
            MovePattern.PAWN_START_MOVE_OF_BLACK
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();
        return isMovePattern(movePattern, board, source, targetPiece, color);
    }

    @Override
    protected boolean isMovePattern(final MovePattern movePattern,
                                  final Board board,
                                  final Position source,
                                  final Piece targetPiece,
                                  final Color color) {
        if (!BLACK_MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_OF_BLACK) {
            return isStartMove(board, source, targetPiece, color);
        }
        if (movePattern == MovePattern.SOUTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.SE || movePattern == MovePattern.SW) {
            return isCatchable(targetPiece, color);
        }
        return false;
    }
}
