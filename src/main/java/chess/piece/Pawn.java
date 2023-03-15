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
        if (isOutOfMovementRadius(targetPiece,subtractedRow, subtractedColumn)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        // 1. 가로 방향 방지
        // 2. 뒤로 가기 방지
        // 3. 대각선 방지
        // 3. 첫 이동 시, 두 칸 앞으로 허용
        if (isSameTeam(Team.WHITE)) {
            if (isWhitePawnTwoStraightDirection(subtractedRow,subtractedColumn)){
                if (coordinate().isWhitePawnStartRow() && targetPiece.isSameTeam(Team.EMPTY)){
                    return false;
                }
                return true;
            }
            if (isWhitePawnOneStraightDirection(subtractedRow, subtractedColumn)) {
                if (targetPiece.isSameTeam(Team.EMPTY)) {
                    return false;
                }
                return true;
            }
            
            if (isWhitePawnDiagonalDirection(subtractedRow, subtractedColumn)) {
                if (targetPiece.isSameTeam(Team.BLACK)) {
                    return false;
                }
                return true;
            }
            return true;
        }
        
        if (isSameTeam(Team.BLACK)) {
            if (isBlackPawnTwoStraightDirection(subtractedRow,subtractedColumn)) {
                if (coordinate().isBlackPawnStartRow() && targetPiece.isSameTeam(Team.EMPTY)) {
                    return false;
                }
                return true;
            }
            
            if (isBlackPawnOneStraightDirection(subtractedRow, subtractedColumn)) {
                if (targetPiece.isSameTeam(Team.EMPTY)) {
                    return false;
                }
                return true;
            }
            
            if (isBlackPawnDiagonalDirection(subtractedRow, subtractedColumn)) {
                if (targetPiece.isSameTeam(Team.WHITE)) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    
    private boolean isWhitePawnOneStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && subtractedColumn == 0;
    }
    
    private boolean isBlackPawnOneStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && subtractedColumn == 0;
    }
    
    private boolean isWhitePawnDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && Math.abs(subtractedColumn) == 1;
    }
    
    private boolean isBlackPawnDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && Math.abs(subtractedColumn) == 1;
    }
    
    private boolean isWhitePawnTwoStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 2 && subtractedColumn == 0;
    }
    
    private boolean isBlackPawnTwoStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -2 && subtractedColumn == 0;
    }
}
