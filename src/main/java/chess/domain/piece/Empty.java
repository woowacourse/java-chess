package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

public class Empty extends Piece {
    public Empty(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'e';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        return false;
    }
}
