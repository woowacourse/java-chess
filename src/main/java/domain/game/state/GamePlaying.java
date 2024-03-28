package domain.game.state;

import domain.game.TeamColor;

public abstract sealed class GamePlaying implements GameState permits WhiteTurn, BlackTurn {
    @Override
    public boolean isContinuable() {
        return true;
    }

    @Override
    public boolean isTurnOf(TeamColor teamColor) {
        return teamColor.equals(currentTurn());
    }

    @Override
    public TeamColor getWinner() {
        throw new IllegalStateException("아직 게임이 진행중입니다.");
    }
}
