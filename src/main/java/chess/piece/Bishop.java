package chess.piece;

import chess.piece.coordinate.Coordinate;

public class Bishop extends Piece {
    public Bishop(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public Bishop(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'b';
    }
}
