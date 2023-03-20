package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Piece {
    protected static final int ROW_INDEX = 1;
    protected static final int COLUMN_INDEX = 0;
    
    private final Team team;
    protected final Coordinate coordinate;
    
    protected Piece(Team team, Coordinate coordinate) {
        this.team = team;
        this.coordinate = coordinate;
    }
    
    public abstract char symbol();
    
    public abstract boolean isMovable(Piece targetPiece);
    
    public Piece movedSourcePiece(Coordinate coordinate) {
        return PieceMatcher.of(symbol(), team, coordinate);
    }
    
    public boolean isSameTeam(Team otherTeam) {
        return this.team.isSameTeam(otherTeam);
    }
    
    protected int calculateRowOrColumnDistance(Piece targetPiece, int rowOrColumnIndex) {
        return convertAbsoluteValue(targetPiece).get(rowOrColumnIndex);
    }
    
    private List<Integer> convertAbsoluteValue(Piece targetPiece) {
        return subtractCoordinate(targetPiece)
                .stream()
                .map(Math::abs)
                .collect(Collectors.toUnmodifiableList());
    }
    
    protected List<Integer> subtractCoordinate(Piece targetPiece) {
        return this.coordinate.calculateCoordinateDistance(targetPiece.coordinate);
    }
    
    protected boolean isDifferentTeam(Piece otherPiece) {
        return this.team != otherPiece.team;
    }
    
    protected boolean isBothZero(int rowDistance, int columnDistance) {
        return rowDistance == 0 && columnDistance == 0;
    }
    
    public int compareToPieceByRowNum(Piece piece) {
        return coordinate.compareToPieceByRowNum(piece.coordinate);
    }
    
    public boolean isKnight() {
        return false;
    }
    
    public boolean isSameCoordinate(Coordinate coordinate) {
        return this.coordinate.equals(coordinate);
    }
    
    public int row() {
        return coordinate.row();
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
