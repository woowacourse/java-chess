package piece;

import piece.coordinate.Coordinate;

public class Rook extends Piece {
    public Rook(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public Rook(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'r';
    }
}
