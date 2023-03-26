package chess.domain.piece;

import chess.domain.board.InitialPieceTypes;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;

import java.util.Objects;
import java.util.Set;

public abstract class Piece {
    protected final Team team;
    
    protected Piece(Team team) {
        this.team = team;
    }
    
    public static Piece from(Coordinate coordinate) {
        InitialPieceTypes initialPieceTypes = InitialPieceTypes.from(coordinate);
        PieceType pieceType = initialPieceTypes.findPieceTypeByColumn(coordinate);
        return pieceType.makePiece(Team.from(coordinate));
    }
    
    public abstract Set<Direction> directions();
    
    public abstract PieceMovingType movingType();
    
    public abstract PieceType pieceType();
    
    public Piece movedPiece() {
        return this;
    }
    
    public boolean isSameTeam(Piece otherPiece) {
        return this.team.isSameTeam(otherPiece.team);
    }
    
    public boolean isTeam(Team currentTeam) {
        return this.team.isSameTeam(currentTeam);
    }
    
    public boolean isOppositeTeam(Piece otherPiece) {
        return this.team.isDifferentTeam(otherPiece.team);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
