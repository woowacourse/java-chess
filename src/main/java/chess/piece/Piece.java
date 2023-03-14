package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team && Objects.equals(coordinate, piece.coordinate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(team, coordinate);
    }
    
    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", coordinate=" + coordinate +
                '}';
    }
}
