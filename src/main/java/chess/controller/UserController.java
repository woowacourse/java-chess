package chess.controller;

import chess.dto.UserCommand;
import chess.dto.UserRequest;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class UserController {

    private final UserService userService;
    private final InputView inputView;
    private final OutputView outputView;

    public UserController(final UserService userService, final InputView inputView, final OutputView outputView) {
        this.userService = userService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public long start() {
        printEntrance();
        UserRequest request = inputView.readUserRequest();
        if (request.getCommand() == UserCommand.LOGIN) {
            return userService.login(request.getName());
        }
        return userService.signup(request.getName());
    }

    private void printEntrance() {
        outputView.printUserEntranceMessage();
        List<String> userNames = userService.findUserNames();
        outputView.printUserStatus(userNames);
    }
}
