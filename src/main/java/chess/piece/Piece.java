package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected static final int ROW_INDEX = 0;
    protected static final int COLUMN_INDEX = 1;
    
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
    
    protected int calculateRowOrColumnDistance(Piece targetPiece, int rowOrColumnIndex) {
        return calculateCoordinateDistance(targetPiece).get(rowOrColumnIndex);
    }
    
    private List<Integer> calculateCoordinateDistance(Piece targetPiece) {
        return this.coordinate.calculteCoordinateDistance(targetPiece.coordinate);
    }
    
    protected boolean isDifferentTeam(Piece otherPiece) {
        return this.team != otherPiece.team;
    }
    
    public int compareTo(Piece piece) {
        return coordinate.compareToPieceByRowNum(piece.coordinate);
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
