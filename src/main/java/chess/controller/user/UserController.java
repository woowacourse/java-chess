package chess.controller.user;

import chess.controller.CommandMapper;
import chess.controller.Controller;
import chess.controller.session.RoomSession;
import chess.controller.session.User;
import chess.controller.session.UserSession;
import chess.dto.NameDto;
import chess.dto.UserDto;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class UserController implements Controller {
    private final UserService userService;
    private final CommandMapper<UserCommand, UserAction> commandMapper;

    public UserController(final UserService userService, final CommandMapper<UserCommand, UserAction> commandMapper) {
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
            final List<String> commands = InputView.readUserCommand(UserSession.getName(), RoomSession.getName());
            final UserCommand command = UserCommand.from(commands);
            command.validateCommandsSize(commands);
            final UserAction action = commandMapper.getValue(command);
            action.execute(commands);
            return command;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e.getMessage());
            return UserCommand.EMPTY;
        }
    }

    private void register(final List<String> commands) {
        final NameDto nameDto = new NameDto(commands.get(UserCommand.NAME_INDEX));
        userService.save(nameDto);
    }

    private void login(final List<String> commands) {
        final NameDto nameDto = new NameDto(commands.get(UserCommand.NAME_INDEX));
        final UserDto userDto = userService.findByName(nameDto);
        final User user = new User(userDto.getId(), userDto.getName());
        UserSession.add(user);
    }

    private void logout() {
        UserSession.remove();
    }
}
