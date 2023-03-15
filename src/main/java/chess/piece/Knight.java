package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Knight extends Piece {
    public Knight(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'n';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        return false;
    }
}
