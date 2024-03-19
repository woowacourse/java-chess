package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

public class Knight extends AbstractPiece {

    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        if (isMovablePosition(targetPosition)) {
            return SUCCESS;
        }
        return FAILURE;
    }

    private boolean isMovablePosition(Position targetPosition) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        return (absRowDistance == 2 && absColDistance == 1) || (absRowDistance == 1 && absColDistance == 2);
    }
}
