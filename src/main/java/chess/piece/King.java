package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;

public class King extends Piece {
    
    private static final int MAX_DIFFERENCE_OF_KING = 1;
    
    public King(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        List<Integer> coordinateDistance = this.coordinate().calculteCoordinateDistance(targetPiece.coordinate());
        int rowDistance = coordinateDistance.get(0);
        int columnDistance = coordinateDistance.get(MAX_DIFFERENCE_OF_KING);
        return isKingMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isKingMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOverMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
    
        return !isSameTeam(targetPiece);
    }
    
    private boolean isOverMovementRadius(int rowDistance, int columnDistance) {
        return rowDistance > MAX_DIFFERENCE_OF_KING || columnDistance > MAX_DIFFERENCE_OF_KING;
    }
    
    public char symbol() {
        return 'k';
    }
}
