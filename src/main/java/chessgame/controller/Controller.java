package chessgame.controller;

import chessgame.controller.commandstatus.CommandStatus;
import chessgame.controller.commandstatus.RedayCommand;
import chessgame.service.ChessGameService;
import chessgame.view.InputView;
import chessgame.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private final Map<Command, BiFunction<CommandStatus, List<String>, CommandStatus>> executeByCommand = Map.of(
            Command.START, CommandStatus::start,
            Command.MOVE, CommandStatus::move,
            Command.STATUS, (command, commands) -> command.status(),
            Command.END, (command, commands) -> command.end()
    );

    public Controller(final InputView inputView, final OutputView outputView, ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        CommandStatus command = new RedayCommand(chessGameService, outputView);

        do {
            command = readCommend(command);
        } while (command.canContinue());
    }

    private CommandStatus readCommend(CommandStatus commandStatus) {
        try {
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            commandStatus = processCommand(commandStatus, command, commands);
            return commandStatus;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readCommend(commandStatus);
        }
    }

    private CommandStatus processCommand(CommandStatus commandStatus, Command command, List<String> commands) {
        return executeByCommand.get(command)
                               .apply(commandStatus, commands);
    }
}
