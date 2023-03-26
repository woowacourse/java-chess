package chess.domain.piece.sliding;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class Queen extends SlidingPiece {
    public Queen(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofQueen();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }
    
    @Override
    public String toString() {
        return "Queen{" +
                "team=" + team +
                '}';
    }
}
