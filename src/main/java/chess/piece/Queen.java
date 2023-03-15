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
    
        return isQueenMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isQueenMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) ||
                (isDifferentRowColumn(rowDistance, columnDistance) && (isZeroNotExist(rowDistance, columnDistance)));
    }
    
    private boolean isBothZero(int rowDistance, int columnDistance) {
        return rowDistance == 0 && columnDistance == 0;
    }
    
    private boolean isDifferentRowColumn(int rowDistance, int columnDistance) {
        return rowDistance != columnDistance;
    }
    
    private boolean isZeroNotExist(int rowDistance, int columnDistance) {
        return rowDistance != 0 && columnDistance != 0;
    }
}
