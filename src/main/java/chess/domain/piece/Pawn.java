package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;
import chess.domain.piece.coordinate.Row;

public class Pawn extends Piece {
    private static final int WHITE_PAWN_START_ROW = 7;
    private static final int BLACK_PAWN_START_ROW = 2;
    
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        if (isOutOfMovementRadius(targetPiece, subtractCoordinate(targetPiece))) {
            return false;
        }
    
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(Piece targetPiece, Distances distances) {
        if (isSameTeam(Team.WHITE)) {
            return isWhitePawnImmovable(targetPiece, distances);
        }
        
        if (isSameTeam(Team.BLACK)) {
            return isBlackPawnMovable(targetPiece, distances);
        }
        return true;
    }
    
    private boolean isWhitePawnImmovable(Piece targetPiece, Distances distances) {
        if (distances.equals(new Distances(0, 2))) {
            return isPawnImmovableForwardTwoSpace(targetPiece, WHITE_PAWN_START_ROW);
        }
        if (distances.absoluteColumn().equals(new Distances(1, 1))) {
            return isPawnImmovableForwardOneSpace(targetPiece, Team.BLACK);
        }
        if (distances.equals(new Distances(0, 1))) {
            return isPawnImmovableForwardOneSpace(targetPiece, Team.EMPTY);
        }
        return true;
    }
    
    private boolean isPawnImmovableForwardTwoSpace(Piece targetPiece, int startRow) {
        return !coordinate.isSameRow(Row.from(startRow)) || !targetPiece.isSameTeam(Team.EMPTY);
    }
    
    private boolean isPawnImmovableForwardOneSpace(Piece targetPiece, Team team) {
        return !targetPiece.isSameTeam(team);
    }
    
    private boolean isBlackPawnMovable(Piece targetPiece, Distances distances) {
        if (distances.equals(new Distances(0, -2))) {
            return isPawnImmovableForwardTwoSpace(targetPiece, BLACK_PAWN_START_ROW);
        }
        if (distances.absoluteColumn().equals(new Distances(1, -1))) {
            return isPawnImmovableForwardOneSpace(targetPiece, Team.WHITE);
        }
        if (distances.equals(new Distances(0, -1))) {
            return isPawnImmovableForwardOneSpace(targetPiece, Team.EMPTY);
        }
        return true;
    }
}
