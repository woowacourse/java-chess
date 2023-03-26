package chess.domain.piece.nonsliding;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class Knight extends NonSlidingPiece {
    public Knight(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofKnight();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }
    
    @Override
    public String toString() {
        return "Knight{" +
                "team=" + team +
                '}';
    }
}
