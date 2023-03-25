package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;
import chess.view.SymbolMatcher;

public class Rook extends Piece {
    public Rook(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public SymbolMatcher symbol() {
        return SymbolMatcher.ROOK;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        int rowDistance = calculateRowOrColumnDistance(targetPiece, ROW_INDEX);
        int columnDistance = calculateRowOrColumnDistance(targetPiece, COLUMN_INDEX);
        return isRookMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isRookMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) || isBothNotZero(rowDistance, columnDistance);
    }
    
    private boolean isBothNotZero(int rowDistance, int columnDistance) {
        return rowDistance != 0 && columnDistance != 0;
    }
}
