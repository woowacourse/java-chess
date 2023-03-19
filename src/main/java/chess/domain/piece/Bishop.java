package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

public class Bishop extends Piece {
    public Bishop(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    
    @Override
    public char symbol() {
        return 'b';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        int rowDistance = calculateRowOrColumnDistance(targetPiece, ROW_INDEX);
        int columnDistance = calculateRowOrColumnDistance(targetPiece, COLUMN_INDEX);
        return isBishopMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isBishopMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) || isBothDifferent(rowDistance, columnDistance);
    }
    
    private boolean isBothDifferent(int rowDistance, int columnDistance) {
        return rowDistance != columnDistance;
    }
}
