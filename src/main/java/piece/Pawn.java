package piece;

import piece.coordinate.Coordinate;

public class Pawn extends Piece {
    public Pawn(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'p';
    }
}
