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

    public Pawn(Team team) {
        super(team);
    }

//    public double getScore(long size) {
//        if (size == 1) {
//            return SCORE;
//        }
//        return DUPLICATED_PAWN_SCORE;
//    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        if (isInitPosition(source)) {
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
    public List<Position> getIntervalPosition(Position source, Position target) {
        if (source.isTwoStepAway(target)) {
            return List.of(target, source.getUpVerticalPosition(team.getForwardDirection()));
        }
        return List.of(target);
    }

    @Override
    public boolean isKill(Position source, Position target, Piece targetPiece) {
        return source.isOneStepDiagonal(target, team.getForwardDirection()) && isOtherTeam(targetPiece);
    }

    private boolean isInitPosition(Position position) {
        if (Team.BLACK.equals(team)) {
            return position.isInitPawnPosition(Rank.SEVEN);
        }
        return position.isInitPawnPosition(Rank.TWO);
    }
}
