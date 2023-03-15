package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.Objects;

public abstract class Piece {
    
    private final Team team;
    private final Coordinate coordinate;
    
    protected Piece(Team team, Coordinate coordinate) {
        this.team = team;
        this.coordinate = coordinate;
    }
    
    public abstract char symbol();
    
    public abstract boolean isMovable(Piece targetPiece);
    
    public boolean isWhiteTeam() {
        return team.isWhiteTeam();
    }
    
    public boolean isBlackTeam() {
        return team.isBlackTeam();
    }
    
    protected boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }
    
    public int compareTo(Piece piece) {
        return coordinate.compareToPieceByRowNum(piece.coordinate);
    }
    
    protected Coordinate coordinate(){
        return coordinate;
    }
    
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
