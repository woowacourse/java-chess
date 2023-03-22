package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;

public class King extends Piece {
    public King(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public boolean isMovable(Piece destinationPiece) {
        if (isOutOfMovementRadius(convertAbsoluteValue(destinationPiece))) {
            return false;
        }
    
        return isDifferentTeam(destinationPiece);
    }
    
    private boolean isOutOfMovementRadius(Distances distances) {
        return distances.isBothZero() || distances.isEitherGreaterThenOne();
    }
    
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
