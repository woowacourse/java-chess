package chess.piece;

import chess.Position;
import chess.Rank;
import chess.Team;

public class Pawn extends Piece {
    private static final int ONE_STEP = 1;
    private static final int INIT_DISTANCE = 2;

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
        if (isInitPosition()) {
            return this.position.isStepForward(position, team.getForwardDirection(), ONE_STEP) ||
                    this.position.isStepForward(position, team.getForwardDirection(), INIT_DISTANCE);
        }
        return this.position.isStepForward(position, team.getForwardDirection(), ONE_STEP);
    }

    private boolean isInitPosition() {
        if (Team.BLACK.equals(team)) {
            return position.isInitPawnPosition(Rank.SEVEN);
        }
        return position.isInitPawnPosition(Rank.TWO);
    }
}
