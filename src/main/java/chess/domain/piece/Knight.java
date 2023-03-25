package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

public class Knight extends Piece {
    public Knight(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public SymbolMatcher symbol() {
        return SymbolMatcher.KNIGHT;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        int rowDistance = calculateRowOrColumnDistance(targetPiece, ROW_INDEX);
        int columnDistance = calculateRowOrColumnDistance(targetPiece, COLUMN_INDEX);
        return isKnightMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isKnightMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) || isNotContainsOneAndTwo(rowDistance, columnDistance);
    }
    
    private boolean isNotContainsOneAndTwo(int rowDistance, int columnDistance) {
        return !((rowDistance == 1 && columnDistance == 2) || (rowDistance == 2 && columnDistance == 1));
    }
    
    @Override
    public boolean isKnight() {
        return true;
    }
}
