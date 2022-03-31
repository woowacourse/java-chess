package chess.model.piece;

import chess.model.Position;
import chess.model.Rank;
import chess.model.Team;

import java.util.List;

public class Pawn extends Piece {
    private static final int ONE_STEP = 1;
    private static final int INIT_DISTANCE = 2;
    private static final String BLACK_NAME = "P";
    private static final String WHITE_NAME = "p";
    private static final double SCORE = 1D;
    private static final double DUPLICATED_PAWN_SCORE = 0.5D;

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    public double getScore(long size) {
        if (size == 1) {
            return SCORE;
        }
        return DUPLICATED_PAWN_SCORE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isMovable(Position target) {
        if (isInitPosition()) {
            return this.position.isStepForward(target, team.getForwardDirection(), ONE_STEP) ||
                    this.position.isStepForward(target, team.getForwardDirection(), INIT_DISTANCE);
        }
        return this.position.isStepForward(target, team.getForwardDirection(), ONE_STEP);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        if (isInitPosition()) {
            return source.isStepForward(target, team.getForwardDirection(), ONE_STEP) ||
                    source.isStepForward(target, team.getForwardDirection(), INIT_DISTANCE);
        }
        return source.isStepForward(target, team.getForwardDirection(), ONE_STEP);
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

    @Override
    public boolean isKill(Piece other) {
        return this.position.isOneStepDiagonal(other.position, team.getForwardDirection()) && isOtherTeam(other);
    }

    private boolean isInitPosition() {
        if (Team.BLACK.equals(team)) {
            return position.isInitPawnPosition(Rank.SEVEN);
        }
        return position.isInitPawnPosition(Rank.TWO);
    }
}
