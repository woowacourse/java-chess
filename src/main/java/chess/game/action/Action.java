package chess.game.action;

import chess.game.GameStatus;
import chess.game.GameCommand;

public interface Action {

    GameStatus execute(GameCommand gameCommand);
}
