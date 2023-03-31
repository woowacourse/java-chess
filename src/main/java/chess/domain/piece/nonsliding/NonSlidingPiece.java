package chess.domain.piece.nonsliding;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovingType;
import chess.domain.piece.Team;

public abstract class NonSlidingPiece extends Piece {
    
    protected NonSlidingPiece(Team team) {
        super(team);
    }
    
    @Override
    public PieceMovingType movingType() {
        return PieceMovingType.NON_SLIDING;
    }
}
