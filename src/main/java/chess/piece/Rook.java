package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Rook extends Piece {
    public Rook(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'r';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        return false;
    }
}
