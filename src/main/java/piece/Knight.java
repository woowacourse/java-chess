package piece;

import piece.coordinate.Coordinate;

public class Knight extends Piece {
    public Knight(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public Knight(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'n';
    }
}
