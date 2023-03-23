package controller.command;

import controller.GameStatus;

public class EndCommand implements GameCommand {

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        System.out.println("끝");
        return GameStatus.END;
    }

}

