package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;
import chess.board.piece.position.Rank;

import java.util.List;

public final class Pawn extends Piece {
    private static final int ONE_STEP = 1;
    private static final int INIT_DISTANCE = 2;
    private static final String BLACK_NAME = "P";
    private static final String WHITE_NAME = "p";
    private static final double SCORE = 1D;
    private static final double DUPLICATED_PAWN_SCORE = 0.5D;
    private static final String TYPE = "pawn";

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    public double getScore(long size) {
        if (size == 1) {
            return SCORE;
        }
        return DUPLICATED_PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isMovableRange(Position position) {
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
    public String getType() {
        return TYPE;
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

    @Override
    public boolean isKill(Piece other) {
        return this.position.isOneStepDiagonal(other.position, team.getForwardDirection()) && isOtherTeam(other);
    }
}
