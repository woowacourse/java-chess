package domain.game.state;

import domain.game.TeamColor;

public interface GameState {
    boolean isContinuable();

    boolean isTurnOf(TeamColor teamColor);

    TeamColor currentTurn();

    TeamColor getWinner();
}
