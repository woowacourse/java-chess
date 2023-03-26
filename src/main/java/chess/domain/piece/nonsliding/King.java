package chess.domain.piece.nonsliding;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class King extends NonSlidingPiece {
    public King(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofKing();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
    
    
    
    @Override
    public String toString() {
        return "King{" +
                "team=" + team +
                '}';
    }
}
