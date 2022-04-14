package chess.model.piece;

import chess.model.Team;
import chess.model.position.Direction;
import chess.model.strategy.PawnMoveStrategy;

public class Pawn extends Piece {
    private static final String BLACK_NAME = "P";
    private static final String WHITE_NAME = "p";
    private static final double SCORE = 1D;

    public Pawn(Team team) {
        super(team, new PawnMoveStrategy(Direction.movePawn(team), Direction.attackPawn(team)));
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
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }
}
