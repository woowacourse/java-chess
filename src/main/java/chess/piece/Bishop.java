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
    public boolean isMoveable(Piece targetPiece) {
        return false;
    }
}
