package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class PawnWhiteMoveStrategy extends PawnMoveStrategy {

    private final static List<MovePattern> WHITE_MOVE_PATTERNS = List.of(
            MovePattern.NORTH,
            MovePattern.NORTHEAST,
            MovePattern.NORTHWEST,
            MovePattern.PAWN_START_MOVE_WHITE
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();
        return isWhiteMovePattern(movePattern, board, source, targetPiece, color);
    }

    private boolean isWhiteMovePattern(final MovePattern movePattern,
                                       final Board board,
                                       final Position source,
                                       final Piece targetPiece,
                                       final Color color) {
        if (!WHITE_MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_WHITE) {
            return isStartMove(board, source, targetPiece, color);
        }
        if (movePattern == MovePattern.NORTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.NORTHEAST || movePattern == MovePattern.NORTHWEST) {
            return isCatchable(targetPiece, color);
        }
        return false;
    }
}
