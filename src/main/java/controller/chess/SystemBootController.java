package controller.chess;

import dao.JdbcGameTurnDao;
import dao.JdbcPieceDao;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class SystemBootController extends ChessController {

    protected enum SystemCommandType {
        START,
        END
    }

    public SystemBootController() {
    }

    @Override
    public ChessController run() {
        return readNextController();
    }

    @Override
    protected ChessController readNextController() {
        SystemCommandType command = receiveSystemCommand();
        if (command == SystemCommandType.START) {
            return new InitializeController(new ChessGameService(new JdbcPieceDao(), new JdbcGameTurnDao()));
        }
        return new SystemEndController();
    }

    private SystemCommandType receiveSystemCommand() {
        List<String> userInput = InputView.readUserInput();
        while (!isSystemCommands(userInput.get(0))) {
            OutputView.printNotSystemCommandMessage();
            userInput = InputView.readUserInput();
        }
        return SystemCommandType.valueOf(userInput.get(0).toUpperCase());
    }

    private boolean isSystemCommands(String input) {
        try {
            SystemCommandType.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isEndController() {
        return false;
    }
}
