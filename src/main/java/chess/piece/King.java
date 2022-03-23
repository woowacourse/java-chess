package chess.piece;

import chess.Position;
import chess.Team;

public class King extends Piece {

    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        return isCorrectDirection(position) && isCorrectDistance(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) ||
                this.position.isHorizontal(position) ||
                this.position.isDiagonal(position);
    }

    private boolean isCorrectDistance(Position position) {
        return this.position.isOneStepAway(position);
    }
}
