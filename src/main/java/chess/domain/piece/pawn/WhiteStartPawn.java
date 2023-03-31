package chess.domain.piece.pawn;

import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class WhiteStartPawn extends Pawn {
    public WhiteStartPawn(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofWhiteStartPawn();
    }
    
    @Override
    public Piece movedPiece() {
        return new WhitePawn(team);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_START_PAWN;
    }
    
    @Override
    public String toString() {
        return "WhiteStartPawn{" +
                "team=" + team +
                '}';
    }
}
