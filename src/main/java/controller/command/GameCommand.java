package controller.command;

import controller.GameStatus;

public interface GameCommand {
    GameStatus execute(GameStatus gameStatus);

}

