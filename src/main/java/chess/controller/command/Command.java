package chess.controller.command;

import chess.domain.gamestatus.GameStatus;

public interface Command {
    GameStatus execute(GameStatus gameStatus);
}
