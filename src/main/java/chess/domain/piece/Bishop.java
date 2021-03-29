package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Strategy;
import chess.domain.board.Team;

public class Bishop extends AbstractPiece {

    public static final double POINT = 3;
    private static final int MOVE_RANGE = 8;

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.diagonalDirection(), MOVE_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "B";
        }
        return "b";
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

    @Override
    public String pieceName() {
        return "bishop";
    }
}
