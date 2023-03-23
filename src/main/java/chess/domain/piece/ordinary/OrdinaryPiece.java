package chess.domain.piece.ordinary;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public abstract class OrdinaryPiece extends Piece {

    protected OrdinaryPiece(final Team team, final PieceType pieceType) {
        super(team, pieceType);
    }

    @Override
    public boolean isValidMove(int fileInterval, int rankInterval, Piece target) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!getPieceType().getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
        return true;
    }

    @Override
    public final boolean isValidTeam(final Piece target) {
        return getTeam() != target.getTeam();
    }

    @Override
    public final boolean isInitialPawn() {
        return false;
    }
}
