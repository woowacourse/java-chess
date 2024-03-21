package domain;

import static domain.PieceMoveResult.CATCH;
import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

import java.util.Optional;

abstract class AbstractCatchOnMovePiece extends AbstractPiece {
    AbstractCatchOnMovePiece(Position position, Team team) {
        super(position, team);
    }

    @Override
    public final PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Optional<PieceMoveResult> pieceMoveResult = tryMoveAssumeAloneAndCheckRoute(targetPosition, piecesOnChessBoard);
        if (pieceMoveResult.isPresent()) {
            return pieceMoveResult.get();
        }
        if (isMyTeam(piecesOnChessBoard, targetPosition)) {
            return FAILURE;
        }
        if (isOtherTeam(piecesOnChessBoard, targetPosition)) {
            return CATCH;
        }
        return SUCCESS;
    }

    protected abstract Optional<PieceMoveResult> tryMoveAssumeAloneAndCheckRoute(Position targetPosition,
                                                                                 PiecesOnChessBoard piecesOnChessBoard);

    private boolean isMyTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
        Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
    }

    private boolean isOtherTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
        Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam().otherTeam());
    }
}
