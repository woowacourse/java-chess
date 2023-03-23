package chess.domain.piece;

import chess.domain.board.InitialPieceTypes;
import chess.domain.distance.Distances;
import chess.domain.piece.coordinate.Coordinate;

import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    protected final Coordinate coordinate;
    
    protected Piece(Team team, Coordinate coordinate) {
        this.team = team;
        this.coordinate = coordinate;
    }
    
    public static Piece from(Coordinate coordinate) {
        Team team = Team.from(coordinate);
        InitialPieceTypes initialPieceTypes = InitialPieceTypes.from(coordinate);
        
        return initialPieceTypes.findPieceTypeByColumn(coordinate)
                .makePiece(team, coordinate);
    }
    
    public abstract PieceType pieceType();
    
    public abstract boolean isMovable(Piece targetPiece);
    
    public Piece movedSourcePiece(Coordinate coordinate) {
        return pieceType().makePiece(team, coordinate);
    }
    
    public boolean isSameTeam(Team otherTeam) {
        return this.team.isSameTeam(otherTeam);
    }
    
    protected Distances convertAbsoluteValue(Piece targetPiece) {
        return subtractCoordinate(targetPiece).absoluteValue();
    }
    
    protected Distances subtractCoordinate(Piece targetPiece) {
        return this.coordinate.subtractCoordinate(targetPiece.coordinate);
    }
    
    protected boolean isDifferentTeam(Piece otherPiece) {
        return this.team.isDifferentTeam(otherPiece.team);
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
