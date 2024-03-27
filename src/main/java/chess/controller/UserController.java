package chess.controller;

import chess.dto.UserCommand;
import chess.dto.UserRequest;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class UserController {

    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;
    private final RoomController roomController;

    public UserController(final InputView inputView, final OutputView outputView, final UserService userService,
                          final RoomController roomController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
        this.roomController = roomController;
    }

    public void start() {
        printEntrance();
        UserRequest request = inputView.readUserRequest();
        if (request.getCommand() == UserCommand.LOGIN) {
            long userId = userService.login(request.getName());
            roomController.enterRoom(userId);
            return;
        }
        long userId = userService.signup(request.getName());
        roomController.enterRoom(userId);
    }

    private void printEntrance() {
        outputView.printUserEntranceMessage();
        List<String> userNames = userService.findUserNames();
        outputView.printUserStatus(userNames);
    }
}
