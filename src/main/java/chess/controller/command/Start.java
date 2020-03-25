package chess.controller.command;

import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.Running;

public class Start implements Command {
    @Override
    public GameStatus execute(GameStatus gameStatus) {
        return new Running();
    }
}
