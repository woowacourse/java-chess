package chess.domain.piece.ordinary;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public abstract class OrdinaryPiece extends Piece {

    protected OrdinaryPiece(final Team team, final PieceType pieceType) {
        super(team, pieceType);
    }

    @Override
    public void validateSpecialMovement(int fileInterval, int rankInterval) {
    }
}
