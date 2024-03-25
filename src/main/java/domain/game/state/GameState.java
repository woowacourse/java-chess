package domain.game.state;

import domain.game.Board;
import domain.game.MoveResponse;
import domain.game.TeamColor;
import domain.position.Position;

public interface GameState {
    boolean isContinuable();

    boolean isTurnOf(TeamColor teamColor);

    TeamColor currentTurn();

    MoveResponse move(Board board, Position source, Position destination);

    TeamColor getWinner();
}
