package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;

public class Queen extends Piece {
    public Queen(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'q';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        List<Integer> coordinateDistance = calculateCoordinateDistance(targetPiece);
        int rowDistance = coordinateDistance.get(0);
        int columnDistance = coordinateDistance.get(1);
    
        return isQueenMovable(rowDistance, columnDistance);
    }
    
    private boolean isQueenMovable(int rowDistance, int columnDistance) {
        if (isCorrectMovementRadius(rowDistance, columnDistance)) {
            return true;
        }
        
        return false;
    }
    
    private boolean isCorrectMovementRadius(int rowDistance, int columnDistance) {
        return isDiffrentCoordinate(rowDistance, columnDistance) &&
                (isStraight(rowDistance, columnDistance) || isDiagonal(rowDistance, columnDistance));
    }
    
    private boolean isDiagonal(int rowDistance, int columnDistance) {
        return rowDistance == columnDistance;
    }
    
    private boolean isStraight(int rowDistance, int columnDistance) {
        return rowDistance == 0 || columnDistance == 0;
    }
    
    private boolean isDiffrentCoordinate(int rowDistance, int columnDistance) {
        return !(rowDistance == 0 && columnDistance == 0);
    }
}
