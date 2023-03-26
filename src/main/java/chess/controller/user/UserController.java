package chess.controller.user;

import static chess.controller.user.UserCommand.EMPTY;
import static chess.controller.user.UserCommand.END;
import static chess.controller.user.UserCommand.LOGIN;
import static chess.controller.user.UserCommand.LOGOUT;
import static chess.controller.user.UserCommand.NAME_INDEX;
import static chess.controller.user.UserCommand.REGISTER;

import chess.controller.Action;
import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import chess.domain.user.User;
import chess.service.UserService;
import chess.view.input.UserInputView;
import chess.view.output.UserOutputView;
import java.util.List;
import java.util.Map;

public class UserController implements Controller {
    private final UserInputView inputView;
    private final UserOutputView outputView;
    private final UserService userService;
    private final CommandMapper<UserCommand, Action> commandMapper;

    public UserController(
            final UserInputView inputView,
            final UserOutputView outputView,
            final UserService userService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
        this.commandMapper = new CommandMapper<>(mappingCommand());
    }

    private Map<UserCommand, Action> mappingCommand() {
        return Map.of(
                REGISTER, this::register,
                LOGIN, this::login,
                LOGOUT, ignore -> logout(),
                END, Action.EMPTY
        );
    }

    @Override
    public void run() {
        UserCommand command = EMPTY;
        while (command != END) {
            command = play();
        }
    }

    private UserCommand play() {
        try {
            final List<String> commands = inputView.readCommand(UserSession.getName(), RoomSession.getName());
            final UserCommand command = UserCommand.from(commands);
            command.validateCommandsSize(commands);
            final Action action = commandMapper.getValue(command);
            action.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return EMPTY;
        }
    }

    private void register(final List<String> commands) {
        final String name = commands.get(NAME_INDEX);
        userService.save(name);
        outputView.printRegisterSuccess(name);
    }

    private void login(final List<String> commands) {
        if (UserSession.get() != null) {
            throw new IllegalArgumentException("이미 로그인 된 상태입니다.");
        }
        final String name = commands.get(NAME_INDEX);
        final User user = userService.findByName(name);
        UserSession.add(user);
        outputView.printLoginSuccess(user.getName());
    }

    private void logout() {
        UserSession.remove();
    }
}
