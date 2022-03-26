package chess.piece;

import chess.Position;
import chess.Rank;
import chess.Team;

import java.util.List;

public class Pawn extends Piece {
    private static final int ONE_STEP = 1;
    private static final int INIT_DISTANCE = 2;
    private static final String BLACK_NAME = "P";
    private static final String WHITE_NAME = "p";

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

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        if (this.position.isTwoStepAway(targetPiece.position)) {
            return List.of(targetPiece.position, this.position.getUpVerticalPosition(team.getForwardDirection()));
        }
        return List.of(targetPiece.position);
    }

    private boolean isInitPosition() {
        if (Team.BLACK.equals(team)) {
            return position.isInitPawnPosition(Rank.SEVEN);
        }
        return position.isInitPawnPosition(Rank.TWO);
    }
}
