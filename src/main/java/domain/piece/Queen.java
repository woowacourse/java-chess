package domain.piece;

import static domain.PieceMoveResult.FAILURE;
import static domain.piece.PieceType.QUEEN;

import domain.PieceMoveResult;
import domain.PiecesOnChessBoard;
import domain.Position;
import domain.Team;
import java.util.Optional;

public final class Queen extends AbstractStraightMovePiece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        if (absColDistance != absRowDistance && nowPosition.isOtherColumn(targetPosition) && nowPosition.isOtherRow(
                targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return QUEEN;
    }
}
