package domain;

import static domain.PieceMoveResult.FAILURE;
import static domain.PieceMoveResult.SUCCESS;

public class Pawn extends AbstractPiece {

    private final Position initialPosition;

    public Pawn(Position position, Team team) {
        super(position, team);
        this.initialPosition = position;
    }

    @Override
    public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        if (isMoveForward(nowPosition, targetPosition)) {
            return SUCCESS;
        }
        if (isMoveForwardTwo(targetPosition, nowPosition)) {
            return SUCCESS;
        }
        return FAILURE;
    }

    private boolean isMoveForwardTwo(Position targetPosition, Position nowPosition) {
        return nowPosition.rowDistance(targetPosition) == 2 * forwardDirection() && nowPosition.isSameColumn(
                targetPosition)
                && nowPosition.equals(initialPosition);
    }

    private boolean isMoveForward(Position nowPosition, Position targetPosition) {
        return nowPosition.rowDistance(targetPosition) == forwardDirection() && nowPosition.isSameColumn(
                targetPosition);
    }

    private int forwardDirection() {
        Team team = getTeam();
        if (team.equals(Team.WHITE)) {
            return 1;
        }
        return -1;
    }
}
