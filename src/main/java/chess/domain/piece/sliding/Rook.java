package chess.domain.piece.sliding;

import chess.domain.direction.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Set;

public class Rook extends SlidingPiece {
    public Rook(Team team) {
        super(team);
    }
    
    @Override
    public Set<Direction> directions() {
        return Direction.ofRook();
    }
    
    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }
    
    @Override
    public String toString() {
        return "Rook{" +
                "team=" + team +
                '}';
    }
}
