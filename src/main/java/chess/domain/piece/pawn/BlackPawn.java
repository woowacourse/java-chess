package chess.domain.piece.pawn;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class BlackPawn extends Pawn {
    public BlackPawn(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofBlackPawn();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.BLACK_PAWN;
    }
    
    @Override
    public String toString() {
        return "BlackPawn{" +
                "team=" + team +
                '}';
    }
}
