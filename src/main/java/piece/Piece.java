package piece;

import coordinate.Coordinate;

public class Piece {
    
    private final Team team;
    private final Coordinate coordinate;
    
    public Piece(Team team, int row, char column) {
        this(team,new Coordinate(row,column));
    }
    
    public Piece(Team team, Coordinate coordinate) {
        this.team = team;
        this.coordinate = coordinate;
    }
}
