package chess.controller;

import chess.controller.util.InputExceptionHandler;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FrontController {

    private static final ChessController chessController = new ChessController();
    private static final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler(
            OutputView::printInputErrorMessage);
    private static AppStatus appStatus = AppStatus.RUNNING;

    private FrontController() {
    }

    public static void run() {
        OutputView.printGuideMessage();
        while (appStatus == AppStatus.RUNNING) {
            appStatus = inputExceptionHandler.retryExecuteIfInputIllegal(InputView::requestGameCommand,
                    ActionMapper::execute);
        }
    }

    private enum ActionMapper {

        START(Command.START, chessController::start),
        MOVE(Command.MOVE, chessController::move),
        END(Command.END, chessController::end),
        FORCE_QUIT(Command.EXIT, chessController::forceQuit);

        private static final Map<Command, CommandAction> actionByCommand = Arrays.stream(values())
                .collect(Collectors.toMap(value -> value.command, value -> value.commandAction));
        private final Command command;
        private final CommandAction commandAction;

        ActionMapper(final Command command, final CommandAction commandAction) {
            this.command = command;
            this.commandAction = commandAction;
        }

        private static AppStatus execute(CommandRequest commandRequest) {
            CommandAction action = actionByCommand.getOrDefault(commandRequest.getCommand(),
                    request -> {
                        throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
                    });
            return action.execute(commandRequest);
        }

    }
}
