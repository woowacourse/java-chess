package chess.piece;

import chess.piece.coordinate.Coordinate;

public class King extends Piece {
    private static final int MAX_DIFFERENCE_OF_KING = 1;
    
    public King(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        int rowDistance = calculateRowOrColumnDistance(targetPiece, ROW_INDEX);
        int columnDistance = calculateRowOrColumnDistance(targetPiece, COLUMN_INDEX);
        return isKingMovable(targetPiece, rowDistance, columnDistance);
    }
    
    private boolean isKingMovable(Piece targetPiece, int rowDistance, int columnDistance) {
        if (isOutOfMovementRadius(rowDistance, columnDistance)) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int rowDistance, int columnDistance) {
        return isBothZero(rowDistance, columnDistance) || isAnyoneOverOne(rowDistance, columnDistance);
    }
    
    private boolean isAnyoneOverOne(int rowDistance, int columnDistance) {
        return rowDistance > MAX_DIFFERENCE_OF_KING || columnDistance > MAX_DIFFERENCE_OF_KING;
    }
    
    public SymbolMatcher symbol() {
        return SymbolMatcher.KING;
    }
}
