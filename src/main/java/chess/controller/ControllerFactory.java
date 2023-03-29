package chess.controller;

import chess.controller.game.GameController;
import chess.controller.main.MainCommand;
import chess.controller.main.MainController;
import chess.controller.room.RoomController;
import chess.controller.user.UserController;
import chess.db.FixedConnectionPool;
import chess.db.JdbcTemplate;
import chess.repository.GameDao;
import chess.repository.GameJdbcDao;
import chess.repository.RoomDao;
import chess.repository.RoomJdbcDao;
import chess.repository.UserDao;
import chess.repository.UserJdbcDao;
import chess.service.GameService;
import chess.service.RoomService;
import chess.service.UserService;
import chess.view.input.InputView;
import chess.view.output.GameOutputView;
import chess.view.output.MainOutputView;
import chess.view.output.RoomOutputView;
import chess.view.output.UserOutputView;
import java.util.Map;
import java.util.Scanner;

public class ControllerFactory {
    private static final MainController INSTANCE;
    private static final InputView INPUT_VIEW = new InputView(new Scanner(System.in));
    private static final JdbcTemplate JDBC_TEMPLATE = new JdbcTemplate(FixedConnectionPool.getInstance());

    static {
        final CommandMapper<MainCommand, SubController> mainCommandMapper = new CommandMapper<>(Map.of(
                MainCommand.USER, userController(),
                MainCommand.ROOM, roomController(),
                MainCommand.START, gameController(),
                MainCommand.END, () -> {
                }
        ));
        INSTANCE = new MainController(INPUT_VIEW, new MainOutputView(), mainCommandMapper);
    }

    private static SubController userController() {
        return new UserController(INPUT_VIEW, new UserOutputView(), new UserService(userDao()));
    }

    private static UserDao userDao() {
        return new UserJdbcDao(JDBC_TEMPLATE);
    }

    private static SubController roomController() {
        return new RoomController(INPUT_VIEW, new RoomOutputView(), new RoomService(roomDao()));
    }

    private static RoomDao roomDao() {
        return new RoomJdbcDao(JDBC_TEMPLATE);
    }

    private static SubController gameController() {
        return new GameController(INPUT_VIEW, new GameOutputView(), new GameService(gameDao()));
    }

    private static GameDao gameDao() {
        return new GameJdbcDao(JDBC_TEMPLATE);
    }

    public static MainController mainController() {
        return INSTANCE;
    }
}
