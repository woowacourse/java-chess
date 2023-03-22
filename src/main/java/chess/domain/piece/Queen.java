package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;

public class Queen extends Piece {
    public Queen(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        if (isOutOfMovementRadius(convertAbsoluteValue(targetPiece))) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(Distances distances) {
        return distances.isBothZero() ||
                (distances.isBothDifferent() && distances.isNotContainsZero());
    }
}
