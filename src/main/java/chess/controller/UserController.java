package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.domain.user.User;
import chess.repository.jdbc.JdbcUserDao;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ReadyCommandType;
import chess.view.dto.ReadyRequest;
import java.util.List;

public class UserController extends Controller {

    private final UserService userService;

    public UserController(InputView inputView, OutputView outputView) {
        super(inputView, outputView);
        this.userService = new UserService(new JdbcUserDao());
    }

    @Override
    public void run() {
        Controller controller = repeat(this::selectUser);
        controller.run();
    }

    private Controller selectUser() {
        printAskUserNameMessages();
        ReadyRequest request = inputView.askReadyCommand();
        if (request.getCommandType() == ReadyCommandType.USE) {
            User user = userService.findByName(request.getName());
            return new RoomController(inputView, outputView, user.getId());
        }
        long userId = userService.create(request.getName());
        return new RoomController(inputView, outputView, userId);
    }

    private void printAskUserNameMessages() {
        List<String> userNames = userService.findUserNames();
        if (userNames.size() > 0) {
            outputView.printSelectUserMessage(userNames);
        }
        outputView.printCreateUserMessage();
    }
}
