package chess.domain.piece;

import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;
import chess.domain.piece.coordinate.Row;

public class Pawn extends Piece {
    private static final int WHITE_PAWN_START_ROW = 7;
    private static final int BLACK_PAWN_START_ROW = 2;
    private static final int FIRST_MOVE_FORWARD_TWO_UP_DISTANCE = 2;
    private static final int FIRST_MOVE_FORWARD_TWO_DOWN_DISTANCE = -2;
    private static final int FIRST_MOVE_FORWARD_ONE_UP_DISTANCE = 1;
    private static final int FIRST_MOVE_FORWARD_ONE_DOWN_DISTANCE = -1;
    
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
    
    @Override
    public boolean isMovable(Piece destinationPiece) {
        if (isOutOfMovementRadius(destinationPiece, subtractCoordinate(destinationPiece))) {
            return false;
        }
    
        return isDifferentTeam(destinationPiece);
    }
    
    private boolean isOutOfMovementRadius(Piece destinationPiece, Distances distances) {
        if (isPawnMoveForwardTwoSpace(distances)) {
            return isPawnImmovableForwardTwoSpace(destinationPiece);
        }
        if (isPawnMoveForwardDiagonalOneSpace(distances)) {
            return isPawnImmovableForwardDiagonalOneSpace(destinationPiece);
        }
        if (isPawnMoveForwardOneSpace(distances)) {
            return isNotEmpty(destinationPiece);
        }
        return true;
    }
    
    private boolean isPawnMoveForwardTwoSpace(Distances distances) {
        if (team.isSameTeam(Team.BLACK)) {
            return distances.equals(new Distances(0, FIRST_MOVE_FORWARD_TWO_DOWN_DISTANCE));
        }
        
        return distances.equals(new Distances(0, FIRST_MOVE_FORWARD_TWO_UP_DISTANCE));
    }
    
    private boolean isPawnImmovableForwardTwoSpace(Piece targetPiece) {
        if (team.isSameTeam(Team.BLACK)) {
            return !coordinate.isSameRow(Row.from(BLACK_PAWN_START_ROW)) || isNotEmpty(targetPiece);
        }
        return !coordinate.isSameRow(Row.from(WHITE_PAWN_START_ROW)) || isNotEmpty(targetPiece);
    }
    
    private boolean isNotEmpty(Piece targetPiece) {
        return !targetPiece.isSameTeam(Team.EMPTY);
    }
    
    private boolean isPawnMoveForwardDiagonalOneSpace(Distances distances) {
        Distances distanceWithAbsoluteColumn = distances.absoluteColumn();
        if (team.isSameTeam(Team.BLACK)) {
            return distanceWithAbsoluteColumn.equals(new Distances(1, FIRST_MOVE_FORWARD_ONE_DOWN_DISTANCE));
        }
        
        return distanceWithAbsoluteColumn.equals(new Distances(1, FIRST_MOVE_FORWARD_ONE_UP_DISTANCE));
    }
    
    private boolean isPawnImmovableForwardDiagonalOneSpace(Piece targetPiece) {
        return targetPiece.isSameTeam(team) || targetPiece.isSameTeam(Team.EMPTY);
    }
    
    private boolean isPawnMoveForwardOneSpace(Distances distances) {
        if (team.isSameTeam(Team.BLACK)) {
            return distances.equals(new Distances(0, FIRST_MOVE_FORWARD_ONE_DOWN_DISTANCE));
        }
        
        return distances.equals(new Distances(0, FIRST_MOVE_FORWARD_ONE_UP_DISTANCE));
    }
}
