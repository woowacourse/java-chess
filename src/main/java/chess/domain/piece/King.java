package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Strategy;
import chess.domain.board.Team;

public class King extends AbstractPiece {

    private static final String NAME = "king";
    public static final double POINT = 0;
    private static final int MOVE_RANGE = 1;


    public King(Team team) {
        super(team, NAME);
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.everyDirection(), MOVE_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "K";
        }
        return "k";
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
