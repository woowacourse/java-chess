package chess.domain.piece;

import chess.domain.Team;

public class Bishop implements Piece{
    private final Team team;

    public Bishop(Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void strategy() {

    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "B";
        }
        return "b";
    }

    @Override
    public void checkTurn(Team team) {
        if (this.team != team) {
            throw new IllegalArgumentException("[ERROR] 상대 팀의 차례입니다.");
        }
    }
}
