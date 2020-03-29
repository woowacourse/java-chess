package chess.controller.command;

import chess.domain.gamestatus.GameStatus;

public class End implements Command {

    public static final String command = "end";

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        return gameStatus.finish();
    }
}
