package chess.domain.piece.sliding;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovingType;
import chess.domain.piece.Team;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(Team team) {
        super(team);
    }
    
    @Override
    public PieceMovingType movingType() {
        return PieceMovingType.SLIDING;
    }
}
