package controller.chess;

import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.List;

public abstract class GameController extends ChessController {

    protected enum GameCommandType {
        MOVE,
        STATUS,
        END
    }

    protected final ChessGameService chessGameService;

    protected GameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    protected ChessController readNextController() {
        List<String> commandInput = receiveGameCommandInput();
        GameCommandType commandType = GameCommandType.valueOf(commandInput.get(0).toUpperCase());

        if (commandType == GameCommandType.MOVE) {
            return new MoveController(chessGameService, commandInput);
        }
        if (commandType == GameCommandType.STATUS) {
            return new StatusController(chessGameService);
        }
        return new GameEndController(chessGameService);
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
