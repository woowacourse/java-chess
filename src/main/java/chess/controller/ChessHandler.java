package chess.controller;

import chess.controller.status.Controller;
import chess.controller.status.StartController;
import chess.service.ChessGameService;
import chess.service.ServiceManager;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public final class ChessHandler {

    private final UserService userService;
    private final ChessGameService chessGameService;

    public ChessHandler(final ServiceManager serviceManager) {
        this.userService = serviceManager.userService();
        this.chessGameService = serviceManager.chessGameService();
    }

    public void run() {
        final Long userId = getUserId();
        OutputView.printStartMessage();
        Controller controller = new StartController(userId, chessGameService);
        while (controller.isRun()) {
            controller = play(controller);
        }
    }

    private Controller play(Controller controller) {
        try {
            final List<String> commands = InputView.getCommands();
            final Command command = Command.findCommand(commands);
            controller = controller.checkCommand(command);
            controller.printBoard();
            return controller;
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return play(controller);
        }
    }

    private Long getUserId() {
        OutputView.printUserNameInputMessage();
        return getUserIdWithRetry(InputView::getCommand);
    }

    private Long getUserIdWithRetry(final Supplier<String> supplier) {
        try {
            final String name = supplier.get();
            return userService.getUserId(name);
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return getUserIdWithRetry(supplier);
        }
    }
}

