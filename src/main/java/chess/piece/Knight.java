package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Knight extends Piece {
    public Knight(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'n';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        int rowDistance = calculateRowOrColumnDistance(targetPiece, ROW_INDEX);
        int columnDistance = calculateRowOrColumnDistance(targetPiece, COLUMN_INDEX);
        return isKnightMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isKnightMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadious(rowDistance, columnDistance)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadious(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) || isNotContainsOneAndTwo(rowDistance, columnDistance);
    }
    
    private boolean isNotContainsOneAndTwo(int rowDistance, int columnDistance) {
        return !((rowDistance == 1 && columnDistance == 2) || (rowDistance == 2 && columnDistance == 1));
    }
}
