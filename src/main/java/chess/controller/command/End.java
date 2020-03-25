package chess.controller.command;

import chess.domain.gamestatus.GameStatus;

public class End implements Command {
    @Override
    public GameStatus execute(GameStatus gameStatus) {
        return null;
    }
}
