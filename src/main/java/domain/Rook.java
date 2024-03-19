package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

public class Rook extends AbstractPiece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        if (nowPosition.isSameColumn(targetPosition) || nowPosition.isSameRow(targetPosition)) {
            return SUCCESS;
        }
        return FAILURE;
    }
}
