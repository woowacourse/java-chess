package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Strategy;
import chess.domain.Team;

public class Pawn implements Piece {

    public static final double POINT = 1;
    public static final int BLACK_PAWN_START_LINE = 7;
    public static final int WHITE_PAWN_START_LINE = 2;
    public static final int MOVE_FIRST_RANGE = 2;

    private final Team team;

    public Pawn(Team team) {
        this.team = team;
    }

    @Override
    public Strategy strategy() {
        if (team == Team.BLACK) {
            return new Strategy(Direction.blackPawnDirection(), MOVE_FIRST_RANGE);
        }
        return new Strategy(Direction.whitePawnDirection(), MOVE_FIRST_RANGE);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "P";
        }
        return "p";
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void checkTurn(Team team) {
        if (this.team != team) {
            throw new IllegalArgumentException("[ERROR] 상대 팀의 차례입니다.");
        }
    }

    @Override
    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
