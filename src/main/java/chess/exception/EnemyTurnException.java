package chess.exception;

import chess.domain.Team;

public class EnemyTurnException extends IllegalArgumentException {
    public EnemyTurnException(final Team team) {
        super(team.name() + "팀 차례입니다.");
    }
}
