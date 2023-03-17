package chess.domain.piece.type.pawn.state;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.pawn.move.PawnColorMoveStrategy;

public class InitialPawn extends PawnState {

    public InitialPawn(final PawnColorMoveStrategy colorMoveStrategy) {
        super(colorMoveStrategy);
    }

    @Override
    public PawnState next(final PiecePosition piecePosition) {
        return new MovedPawn(colorMoveStrategy);
    }

    @Override
    public void validateMovable(final Path path) {
        colorMoveStrategy.validateMovementDirection(path);
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
