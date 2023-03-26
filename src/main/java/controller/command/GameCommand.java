package controller.command;

import dao.ChessBoardDao;
import view.InputView;
import view.OutputView;

import java.util.List;

public abstract class GameCommand extends Command {

    protected enum GameCommandType {
        MOVE,
        STATUS,
        END;
    }

    protected final ChessBoardDao chessBoardDao;

    protected GameCommand(ChessBoardDao chessBoardDao) {
        this.chessBoardDao = chessBoardDao;
    }

    @Override
    protected Command readNextCommand() {
        List<String> commandInput = receiveGameCommandInput();
        GameCommandType commandType = GameCommandType.valueOf(commandInput.get(0).toUpperCase());

        if (commandType == GameCommandType.MOVE) {
            return new Move(chessBoardDao, commandInput);
        }
        if (commandType == GameCommandType.STATUS) {
            return new Status(chessBoardDao);
        }
        return new GameEnd(chessBoardDao);
    }

    private List<String> receiveGameCommandInput() {
        List<String> userInput = InputView.readUserInput();
        while (!isGameCommands(userInput.get(0))) {
            OutputView.printNotGameCommandMessage();
            userInput = InputView.readUserInput();
        }
        return userInput;
    }

    private boolean isGameCommands(String input) {
        try {
            GameCommandType.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
