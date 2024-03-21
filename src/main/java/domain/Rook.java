package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceType.ROOK;

import java.util.Optional;

final class Rook extends AbstractMoveStraightMovePiece {
    Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        if (nowPosition.isOtherColumn(targetPosition) && nowPosition.isOtherRow(targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return ROOK;
    }
}
