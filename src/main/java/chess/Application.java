package chess;

import chess.controller.GameController;
import chess.controller.UserController;
import chess.repository.UserDao;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        UserDao userDao = new UserDao();

        UserService userService = new UserService(userDao);

        GameController gameController = new GameController(inputView, outputView);
        UserController userController = new UserController(userService, inputView, outputView);

        userController.signup();
        gameController.start();
    }
}
