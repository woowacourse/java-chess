package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

import java.util.Optional;

public class King extends AbstractPiece {
    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        if (absRowDistance > 1 || absColDistance > 1) {
            return FAILURE;
        }
        if (isMyTeam(piecesOnChessBoard, targetPosition)) {
            return FAILURE;
        }
        return SUCCESS;
    }

    private boolean isMyTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
        Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
    }
}
