package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final int ONE_STEP = 1;
    private static final int INIT_DISTANCE = 2;
    private static final String BLACK_NAME = "P";
    private static final String WHITE_NAME = "p";
    private static final double SCORE = 1D;
    private static final double DUPLICATED_PAWN_SCORE = 0.5D;

    public Pawn(Team team) {
        super(team, new PawnMoveStrategy(Direction.movePawn(team), Direction.attackPawn(team)));
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
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }
}
