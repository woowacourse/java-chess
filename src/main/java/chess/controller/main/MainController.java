package chess.controller.main;

import static chess.controller.main.MainCommand.EMPTY;
import static chess.controller.main.MainCommand.END;

import chess.controller.CommandMapper;
import chess.controller.SubController;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.view.input.InputView;
import chess.view.output.MainOutputView;

public class MainController {
    private final InputView inputView;
    private final MainOutputView outputView;
    private final CommandMapper<MainCommand, SubController> commandMapper;

    public MainController(
            final InputView inputView,
            final MainOutputView outputView,
            final CommandMapper<MainCommand, SubController> commandMapper
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandMapper = commandMapper;
    }

    public void run() {
        MainCommand command = EMPTY;
        while (command != END) {
            command = readCommand();
        }
        UserSession.remove();
        RoomSession.remove();
    }

    private MainCommand readCommand() {
        try {
            outputView.printCommands(UserSession.getName(), RoomSession.getName());
            final String command = inputView.readCommand();
            final MainCommand mainCommand = MainCommand.from(command);
            final SubController controller = commandMapper.getValue(mainCommand);
            controller.run();
            return mainCommand;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return EMPTY;
        }
    }
}
