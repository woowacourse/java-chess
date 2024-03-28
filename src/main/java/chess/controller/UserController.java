package chess.controller;

import chess.dto.UserCommand;
import chess.dto.UserRequest;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class UserController {

    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;
    private final RoomController roomController;

    public UserController(final InputView inputView,
                          final OutputView outputView,
                          final UserService userService,
                          final RoomController roomController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
        this.roomController = roomController;
    }

    public void start() {
        printEntrance();
        long userId = requestUntilValidated(this::entranceUser);
        roomController.enterRoom(userId);
    }

    private long entranceUser() {
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

    private <T> T requestUntilValidated(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return requestUntilValidated(supplier);
        }
    }
}
