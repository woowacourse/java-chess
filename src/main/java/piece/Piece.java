package piece;

import coordinate.Coordinate;

public abstract class Piece {
    
    private final Team team;
    private final Coordinate coordinate;
    
    protected Piece(Team team, int row, char column) {
        this(team,new Coordinate(row,column));
    }
    
    protected Piece(Team team, Coordinate coordinate) {
        this.team = team;
        this.coordinate = coordinate;
    }
    
    public abstract char symbol();
}
