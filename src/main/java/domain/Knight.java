package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceType.KNIGHT;

import java.util.Optional;

class Knight extends AbstractCatchOnMovePiece {

    Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAloneAndCheckRoute(Position targetPosition,
                                                                     PiecesOnChessBoard piecesOnChessBoard) {
        if (!isMovablePosition(targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    private boolean isMovablePosition(Position targetPosition) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        return (absRowDistance == 2 && absColDistance == 1) || (absRowDistance == 1 && absColDistance == 2);
    }

    @Override
    public PieceType getPieceType() {
        return KNIGHT;
    }
}
