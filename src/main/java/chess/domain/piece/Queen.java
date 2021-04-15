package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Strategy;
import chess.domain.board.Team;

public class Queen extends AbstractPiece {

    private static final String NAME = "queen";
    public static final double POINT = 9;
    private static final int MOVE_RANGE = 8;

    public Queen(Team team) {
        super(team, NAME);
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.everyDirection(), MOVE_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "Q";
        }
        return "q";
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

}
