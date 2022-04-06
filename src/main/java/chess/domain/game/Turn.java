package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private Team now;

    public Turn(final Team now) {
        this.now = now;
    }

    public void passTurn() {
        if (now == Team.NONE) {
            throw new IllegalArgumentException("[ERROR] 입력된 Team 은 게임을 할 수 없습니다.");
        }
        now = now.oppositeTeam();
    }

    public boolean isRightTurn(final Team team) {
        return this.now == team;
    }

    public Team getNow() {
        return now;
    }
}
