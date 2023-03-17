package chess.domain.piece.type.pawn.state;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class InitialPawn implements PawnState {

    @Override
    public PawnState next(final PiecePosition piecePosition) {
        return new MovedPawn();
    }

    @Override
    public void validateMovable(final Path path) {
        if (isPawnSpecialDestination(path)) {
            return;
        }
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("폰은 첫 이동이 아닌 이상 1칸 씩만 이동할 수 있습니다.");
        }
    }

    private boolean isPawnSpecialDestination(final Path path) {
        if (Math.abs(path.rankInterval()) != 2) {
            return false;
        }
        return Math.abs(path.fileInterval()) == 0;
    }
}
