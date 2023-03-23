package domain.piece.nonsliding;

import domain.Square;
import domain.piece.Direction;
import domain.piece.Piece;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    protected NonSlidingPiece(TeamColor teamColor, PieceInfo pieceInfo) {
        super(teamColor, pieceInfo);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Direction direction = dest.calculateVector(src);
        validateDirection(direction);
        return List.of(dest);
    }

    protected abstract void validateDirection(Direction direction);
}
