package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;

public class Pawn extends Piece {
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'p';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        List<Integer> subtractCoordinate = subtractCoordinate(targetPiece);
        int subtractedRow = subtractCoordinate.get(ROW_INDEX);
        int subtractedColumn = subtractCoordinate.get(COLUMN_INDEX);
        return isPawnMovable(targetPiece, subtractedRow, subtractedColumn);
    }
    
    private boolean isPawnMovable(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        System.out.println(isOutOfMovementRadius(subtractedRow, subtractedColumn));
        if (isOutOfMovementRadius(subtractedRow, subtractedColumn)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(int subtractedRow, int subtractedColumn) {
        // 1. 가로 방향 방지
        // 2. 뒤로 가기 방지
        // 3. 대각선 방지
        // 3. 첫 이동 시, 두 칸 앞으로 허용
        if (isWhiteTeam()) {
            
            if (isWhitePawnStraightDirection(subtractedRow, subtractedColumn)) {
                return false;
            }
            
            if (isWhitePawnDiagonalDirection(subtractedRow, subtractedColumn)) {
                return false;
            }
            return true;
        }
        
        if (isBlackTeam()) {
            if (isBlackPawnStraightDirection(subtractedRow, subtractedColumn)) {
                return false;
            }
            
            if (isBlackPawnDiagonalDirection(subtractedRow, subtractedColumn)) {
                return false;
            }
            return true;
        }
        return true;
    }
    
    private boolean isWhitePawnStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && subtractedColumn == 0;
    }
    
    private boolean isBlackPawnStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && subtractedColumn == 0;
    }
    
    private boolean isWhitePawnDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && Math.abs(subtractedColumn) == 1;
    }
    
    private boolean isBlackPawnDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && Math.abs(subtractedColumn) == 1;
    }
}
