package chess.controller.user;

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
    private final CommandMapper<UserCommand, UserAction> commandMapper;

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

    private Map<UserCommand, UserAction> mappingCommand() {
        return Map.of(
                UserCommand.REGISTER, this::register,
                UserCommand.LOGIN, this::login,
                UserCommand.LOGOUT, ignore -> logout(),
                UserCommand.END, UserAction.EMPTY
        );
    }

    @Override
    public void run() {
        UserCommand command = UserCommand.EMPTY;
        while (command != UserCommand.END) {
            command = play();
        }
    }

    private UserCommand play() {
        try {
            final List<String> commands = inputView.readCommand(UserSession.getName(), RoomSession.getName());
            final UserCommand command = UserCommand.from(commands);
            command.validateCommandsSize(commands);
            final UserAction action = commandMapper.getValue(command);
            action.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e.getMessage());
            return UserCommand.EMPTY;
        }
    }

    private void register(final List<String> commands) {
        final String name = commands.get(UserCommand.NAME_INDEX);
        userService.save(name);
        outputView.printRegisterSuccess(name);
    }

    private void login(final List<String> commands) {
        if (UserSession.get() != null) {
            throw new IllegalArgumentException("이미 로그인 된 상태입니다.");
        }
        final String name = commands.get(UserCommand.NAME_INDEX);
        final User user = userService.findByName(name);
        UserSession.add(user);
        outputView.printLoginSuccess(user.getName());
    }

    private void logout() {
        UserSession.remove();
    }
}
