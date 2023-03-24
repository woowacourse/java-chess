package chess.domain.pieces.nonsliding;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public final class King extends NonSlidingPiece {

    static final String INVALID_MOVE_DISTANCE = "킹은 1칸만 갈 수 있습니다.";
    private static final int KING_MOVABLE_DISTANCE = 1;

    public King(final Team team) {
        super(team, Direction.ofAllExpectKnight());
    }

    @Override
    public void validateMove(final Direction correctDirection, final List<Piece> onRoutePieces) {
        validateDirection(correctDirection);
        validateDistance(onRoutePieces);
        validateOnRoutePiecesExistAlly(onRoutePieces);
    }

    private void validateDistance(final List<Piece> otherPieces) {
        if (otherPieces.size() == KING_MOVABLE_DISTANCE) {
            return;
        }
        throw new IllegalArgumentException(INVALID_MOVE_DISTANCE);
    }
}
