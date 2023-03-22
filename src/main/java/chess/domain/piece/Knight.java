package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;

public class Knight extends Piece {
    public Knight(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }
    
    @Override
    public boolean isMovable(Piece destinationPiece) {
        if (isOutOfMovementRadius(convertAbsoluteValue(destinationPiece))) {
            return false;
        }
    
        return isDifferentTeam(destinationPiece);
    }
    
    private boolean isOutOfMovementRadius(Distances distances) {
        return distances.isBothZero() || !distances.isContainsOneAndTwo();
    }
    
    @Override
    public boolean isKnight() {
        return true;
    }
}
