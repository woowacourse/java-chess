package chess.domain.piece.pawn;

import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class BlackStartPawn extends Pawn {
    public BlackStartPawn(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofBlackStartPawn();
    }
    
    @Override
    public Piece movedPiece() {
        return new BlackPawn(team);
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_START_PAWN;
    }
    
    @Override
    public String toString() {
        return "BlackStartPawn{" +
                "team=" + team +
                '}';
    }
}
