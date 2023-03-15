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
        List<Integer> coordinateDistance = calculateCoordinateDistance(targetPiece);
        int rowDistance = coordinateDistance.get(0);
        int columnDistance = coordinateDistance.get(1);
        return isKingMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isKingMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return rowDistance > MAX_DIFFERENCE_OF_KING || columnDistance > MAX_DIFFERENCE_OF_KING;
    }
    
    public char symbol() {
        return 'k';
    }
}
