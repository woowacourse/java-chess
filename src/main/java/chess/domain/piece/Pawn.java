package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;

public class Pawn extends Piece {

    private static final String NAME = "p";

    public Pawn(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public boolean movable(Position currentPosition, Position targetPosition) {
        if (isBehind(currentPosition, targetPosition)) {
            return false;
        }

        int yDif = currentPosition.yDifference(targetPosition);
        int xDif = currentPosition.xDifference(targetPosition);
        return movablePawnRange(yDif) && xDif == 0;
    }

    private boolean isBehind(Position currentPosition, Position targetPosition) {
        if (teamColor() == TeamColor.WHITE) {
            return currentPosition.upperThan(targetPosition);
        }
        return currentPosition.lowerThan(targetPosition);
    }

    private boolean movablePawnRange(int yDif) {
        return yDif == 1 || (this.notMoved() && yDif == 2);
    }
}
