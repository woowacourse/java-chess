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
    public boolean isMovable(Piece destinationPiece) {
        if (isOutOfMovementRadius(convertAbsoluteValue(destinationPiece))) {
            return false;
        }
    
        return isDifferentTeam(destinationPiece);
    }
    
    private boolean isOutOfMovementRadius(Distances distances) {
        return distances.isBothZero() ||
                (distances.isBothDifferent() && distances.isNotContainsZero());
    }
}
