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
import java.util.Map;

public class ControllerFactory {
    private static final Controller INSTANCE;

    static {
        final CommandMapper<MainCommand, Controller> mainCommandMapper = new CommandMapper<>(Map.of(
                MainCommand.USER, userController(),
                MainCommand.START, chessGameController(),
                MainCommand.END, empty()
        ));
        INSTANCE = new MainController(mainCommandMapper);
    }

    private static Controller userController() {
        return new UserController(new UserService(userDao()), new CommandMapper<>());
    }

    private static UserDao userDao() {
        return new UserJdbcDao(jdbcTemplate());
    }

    private static JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(FixedConnectionPool.getInstance());
    }

    private static Controller chessGameController() {
        return new GameController();
    }

    private static Controller empty() {
        return () -> {
        };
    }

    public static Controller mainController() {
        return INSTANCE;
    }
}
