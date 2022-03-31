package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private Team nowTurn;

    public Turn(final Team nowTurn) {
        if (nowTurn == Team.NONE) {
            throw new IllegalArgumentException("[ERROR] 입력된 Team 은 게임을 할 수 없습니다.");
        }
        this.nowTurn = nowTurn;
    }

    public void passTurn() {
        nowTurn = nowTurn.oppositeTeam();
    }

    public boolean isRightTurn(final Team team) {
        return this.nowTurn == team;
    }
}
