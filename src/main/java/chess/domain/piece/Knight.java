package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Strategy;
import chess.domain.Team;

public class Knight implements Piece {
    public static final double POINT = 2.5;
    private static final int MOVE_RANGE = 1;
    private final Team team;

    public Knight(Team team) {
        this.team = team;
    }

    @Override
    public Strategy strategy() {
        return new Strategy(Direction.knightDirection(), MOVE_RANGE);
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
            return "N";
        }
        return "n";
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
