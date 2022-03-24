package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class KnightMoveStrategy extends MoveStrategy {

    private static final List<MovePattern> MOVE_PATTERNS = List.of(
            MovePattern.NNW,
            MovePattern.NNE,
            MovePattern.EEN,
            MovePattern.EES,
            MovePattern.SSE,
            MovePattern.SSW,
            MovePattern.WWS,
            MovePattern.WWN
    );

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final MovePattern movePattern = MovePattern.of(distance.getHorizon(), distance.getVertical());
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();

        if (!MOVE_PATTERNS.contains(movePattern)) {
            return false;
        }
        return isTargetPositionMovable(targetPiece, color);
    }

    @Override
    protected boolean isTargetPositionMovable(final Piece targetPiece, final Color color) {
        if (!targetPiece.isBlank()) {
            return targetPiece.getColor() == color.oppositeColor();
        }
        return true;
    }
}
