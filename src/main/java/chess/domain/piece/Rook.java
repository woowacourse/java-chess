package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Strategy;
import chess.domain.board.Team;

public class Rook extends AbstractPiece {
    private static final String NAME = "rook";
    public static final double POINT = 5;
    private static final int MOVE_RANGE = 8;

    public Rook(Team team) {
        super(team, NAME);
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.linearDirection(), MOVE_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "R";
        }
        return "r";
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
