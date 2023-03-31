package chess.domain.piece.sliding;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class Bishop extends SlidingPiece {
    public Bishop(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofBishop();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.BISHOP;
    }
    
    @Override
    public String toString() {
        return "Bishop{" +
                "team=" + team +
                '}';
    }
}
