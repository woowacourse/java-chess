package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

public class Queen extends AbstractPiece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        if (absColDistance == absRowDistance) {
            return SUCCESS;
        }
        if (nowPosition.isSameColumn(targetPosition) || nowPosition.isSameRow(targetPosition)) {
            return SUCCESS;
        }
        return FAILURE;
    }
}
