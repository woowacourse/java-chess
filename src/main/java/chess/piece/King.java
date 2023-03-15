package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;

public class King extends Piece {
    public King(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public boolean isMoveable(Piece targetPiece) {
        List<Integer> coordinateDistance = this.coordinate().calculteCoordinateDistance(targetPiece.coordinate());
        int rowDistance = coordinateDistance.get(0);
        int columnDistance = coordinateDistance.get(1);
        if (rowDistance > 1 || columnDistance > 1) {
            return false;
        }
        return true;
    }
    
    public char symbol() {
        return 'k';
    }
}
