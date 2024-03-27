package chess.controller;

import chess.dto.UserRequest;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;

public class UserController {

    private final UserService userService;
    private final InputView inputView;
    private final OutputView outputView;

    public UserController(final UserService userService, final InputView inputView, final OutputView outputView) {
        this.userService = userService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void signup() {
        outputView.printSignupMessage();
        UserRequest userRequest = inputView.readUserRequest();
        userService.signup(userRequest.getName());
    }
}
