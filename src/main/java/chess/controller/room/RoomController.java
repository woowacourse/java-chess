package chess.controller.room;

import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.service.RoomService;
import chess.view.input.RoomInputView;
import chess.view.output.RoomOutputView;
import java.util.List;
import java.util.Map;

public class RoomController implements Controller {
    private final RoomInputView inputView;
    private final RoomOutputView outputView;
    private final CommandMapper<RoomCommand, RoomAction> commandMapper;

    public RoomController(
            final RoomInputView inputView,
            final RoomOutputView outputView,
            final RoomService roomService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandMapper = new CommandMapper<>(mappingCommand());
    }

    private Map<RoomCommand, RoomAction> mappingCommand() {
        return Map.of(
                RoomCommand.HISTORY, ignore -> history(),
                RoomCommand.CREATE, this::create,
                RoomCommand.JOIN, this::join,
                RoomCommand.END, RoomAction.EMPTY
        );
    }

    @Override
    public void run() {
        RoomCommand command = RoomCommand.EMPTY;
        while (command != RoomCommand.END) {
            command = play();
        }
    }

    private RoomCommand play() {
        try {
            final List<String> commands = inputView.readCommand(UserSession.getName(), RoomSession.getName());
            final RoomCommand command = RoomCommand.from(commands);
            command.validateCommandsSize(commands);
            final RoomAction action = commandMapper.getValue(command);
            action.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return RoomCommand.EMPTY;
        }
    }

    private void history() {

    }

    private void create(final List<String> commands) {

    }

    private void join(final List<String> commands) {

    }

}
