package chess.domain.pieces.nonsliding;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public final class Knight extends NonSlidingPiece {

    public Knight(final Team team) {
        super(team, Direction.ofKnight());
    }

    @Override
    public void validateMove(final Direction correctDirection, final List<Piece> onRoutePieces) {
        validateDirection(correctDirection);
        validateOnRoutePiecesExistAlly(onRoutePieces);
    }
}
