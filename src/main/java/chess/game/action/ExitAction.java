package chess.game.action;

import chess.game.GameStatus;
import chess.game.GameCommand;

public class ExitAction implements Action {

    @Override
    public GameStatus execute(GameCommand gameCommand) {
        return GameStatus.EXIT;
    }
}
