package chess;

import chess.controller.GameController;
import chess.controller.RoomController;
import chess.controller.UserController;
import chess.infra.JdbcConnectionPool;
import chess.repository.MovementDao;
import chess.repository.MovementRepository;
import chess.repository.RoomDao;
import chess.repository.RoomRepository;
import chess.repository.UserDao;
import chess.repository.UserRepository;
import chess.service.GameService;
import chess.service.RoomService;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        JdbcConnectionPool connectionPool = JdbcConnectionPool.getInstance();

        MovementRepository movementRepository = new MovementDao(connectionPool);
        RoomRepository roomRepository = new RoomDao(connectionPool);
        UserRepository userRepository = new UserDao(connectionPool);

        GameService gameService = new GameService(movementRepository);
        RoomService roomService = new RoomService(roomRepository);
        UserService userService = new UserService(userRepository);

        GameController gameController = new GameController(inputView, outputView, gameService);
        RoomController roomController = new RoomController(inputView, outputView, roomService, gameController);
        UserController userController = new UserController(inputView, outputView, userService, roomController);

        userController.start();

        connectionPool.shutdown();
    }
}
