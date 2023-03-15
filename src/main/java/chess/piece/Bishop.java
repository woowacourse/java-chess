package chess.piece;

import chess.piece.coordinate.Coordinate;

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
        if (rowDistance == 0 && columnDistance == 0 || rowDistance != columnDistance) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
}
