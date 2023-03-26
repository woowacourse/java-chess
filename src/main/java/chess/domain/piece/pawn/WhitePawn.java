package chess.domain.piece.pawn;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class WhitePawn extends Pawn {
    public WhitePawn(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofWhitePawn();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.WHITE_PAWN;
    }
    
    @Override
    public String toString() {
        return "WhitePawn{" +
                "team=" + team +
                '}';
    }
}
