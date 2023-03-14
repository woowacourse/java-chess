package piece;

import piece.coordinate.Coordinate;

public class Empty extends Piece {
    public Empty(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public Empty(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'e';
    }
}
