package chess.domain.piece.state;

import chess.domain.piece.move.PawnColorMoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class MovedPawn extends PawnState {

    protected MovedPawn(final PawnColorMoveStrategy colorMoveStrategy) {
        super(colorMoveStrategy);
    }

    @Override
    public PawnState next(final PiecePosition piecePosition) {
        return this;
    }

    @Override
    public void validateMovable(final Path path) {
        colorMoveStrategy.validateMovementDirection(path);
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("폰은 첫 이동이 아닌 이상 1칸 씩만 이동할 수 있습니다.");
        }
    }
}
