package chess.controller.main;

import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.view.InputView;
import chess.view.OutputView;

public class MainController implements Controller {
    private final CommandMapper<MainCommand, Controller> commandMapper;

    public MainController(final CommandMapper<MainCommand, Controller> commandMapper) {
        this.commandMapper = commandMapper;
    }

    @Override
    public void run() {
        MainCommand command = MainCommand.EMPTY;
        while (command != MainCommand.END) {
            command = readCommand();
        }
    }

    private MainCommand readCommand() {
        try {
            final String command = InputView.readMainCommand(UserSession.getName(), RoomSession.getName());
            final MainCommand mainCommand = MainCommand.from(command);
            final Controller controller = commandMapper.getValue(mainCommand);
            controller.run();
            return mainCommand;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e.getMessage());
            return MainCommand.EMPTY;
        }
    }
}
