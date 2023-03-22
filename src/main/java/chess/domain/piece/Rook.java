package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;

public class Rook extends Piece {
    public Rook(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        if (isOutOfMovementRadius(convertAbsoluteValue(targetPiece))) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(Distances distances) {
        return distances.isBothZero() || distances.isNotContainsZero();
    }
}
