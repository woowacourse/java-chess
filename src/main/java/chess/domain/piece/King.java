package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Strategy;
import chess.domain.Team;

public class King implements Piece {

    public static final double POINT = 0;
    private static final int MOVE_RANGE = 1;
    private final Team team;

    public King(Team team) {
        this.team = team;
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.everyDirection(), MOVE_RANGE);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "K";
        }
        return "k";
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
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
