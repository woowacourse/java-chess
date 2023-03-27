package controller.command;

import controller.GameStatus;

public class EndCommand implements GameCommand {

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        System.out.println("게임을 종료합니다.");
        return GameStatus.END;
    }

}

