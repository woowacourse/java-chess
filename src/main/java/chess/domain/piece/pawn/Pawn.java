package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceMovingType;
import chess.domain.piece.Team;

public abstract class Pawn extends Piece {
    protected Pawn(Team team) {
        super(team);
    }
    
    @Override
    public PieceMovingType movingType() {
        return PieceMovingType.PAWN;
    }
    
    @Override
    public boolean isPawn() {
        return true;
    }
}
