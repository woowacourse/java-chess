package chess.exception;

import chess.domain.Team;

public class AnotherTeamTurnException extends IllegalArgumentException {
    public AnotherTeamTurnException(Team team) {
        super(team.name() + "팀 차례입니다.");
    }
}
