package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.dto.Strategy;
import chess.domain.board.Team;

public class Pawn extends AbstractPiece {

    public static final double POINT = 1;
    public static final int BLACK_PAWN_START_LINE = 7;
    public static final int WHITE_PAWN_START_LINE = 2;
    public static final int MOVE_FIRST_RANGE = 2;
    public static final int MOVE_DEFAULT_RANGE = 1;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public Strategy strategy() {
        if (isBlackTeam()) {
            return new Strategy(Direction.blackPawnDirection(), MOVE_FIRST_RANGE);
        }
        return new Strategy(Direction.whitePawnDirection(), MOVE_FIRST_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "P";
        }
        return "p";
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
