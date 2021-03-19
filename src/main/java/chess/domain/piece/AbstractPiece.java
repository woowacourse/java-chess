package chess.domain.piece;

import chess.domain.board.Team;

public abstract class AbstractPiece implements Piece{
    private final Team team;

    protected AbstractPiece(Team team) {
        this.team = team;
    }

    protected boolean isBlackTeam() {
        return team.isBlack(team);
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
}
