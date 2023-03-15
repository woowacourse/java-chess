package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Pawn extends Piece {
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'p';
    }
    
    @Override
    public boolean isMoveable(Piece targetPiece) {
        return false;
    }
}
