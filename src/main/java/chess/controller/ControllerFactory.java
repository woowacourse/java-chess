package chess.controller;

import chess.controller.game.GameController;
import chess.controller.main.MainCommand;
import chess.controller.main.MainController;
import chess.controller.user.UserController;
import chess.db.FixedConnectionPool;
import chess.db.JdbcTemplate;
import chess.repository.UserDao;
import chess.repository.UserJdbcDao;
import chess.service.UserService;
import chess.view.input.GameInputView;
import chess.view.input.MainInputView;
import chess.view.input.UserInputView;
import chess.view.output.GameOutputView;
import chess.view.output.MainOutputView;
import chess.view.output.UserOutputView;
import java.util.Map;
import java.util.Scanner;

public class ControllerFactory {
    private static final Controller INSTANCE;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final JdbcTemplate JDBC_TEMPLATE = new JdbcTemplate(FixedConnectionPool.getInstance());

    static {
        final CommandMapper<MainCommand, Controller> mainCommandMapper = new CommandMapper<>(Map.of(
                MainCommand.USER, userController(),
                MainCommand.START, chessGameController(),
                MainCommand.END, empty()
        ));
        INSTANCE = new MainController(new MainInputView(SCANNER), new MainOutputView(), mainCommandMapper);
    }

    private static Controller userController() {
        return new UserController(new UserInputView(SCANNER), new UserOutputView(), new UserService(userDao()));
    }

    private static Controller chessGameController() {
        return new GameController(new GameInputView(SCANNER), new GameOutputView());
    }

    private static UserDao userDao() {
        return new UserJdbcDao(JDBC_TEMPLATE);
    }

    private static Controller empty() {
        return () -> {
        };
    }

    public static Controller mainController() {
        return INSTANCE;
    }
}
