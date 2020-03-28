package chess.controller.command;

import chess.domain.gamestatus.GameStatus;

public class Start implements Command {
    @Override
    public GameStatus execute(GameStatus gameStatus) {
        return gameStatus.start();
    }
}
