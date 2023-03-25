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
    public List<Square> findRoutes(Square source, Square destination) {
        Direction direction = destination.calculateVector(source);
        validateDirection(direction);
        return List.of(destination);
    }

    protected abstract void validateDirection(Direction direction);
}
