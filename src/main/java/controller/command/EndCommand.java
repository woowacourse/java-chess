package controller.command;

import controller.GameStatus;
import view.OutputView;

public class EndCommand implements GameCommand {

    private final OutputView outputView;

    public EndCommand(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        outputView.printEndMessage();
        return GameStatus.END;
    }

}

