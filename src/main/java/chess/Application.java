package chess;

import chess.controller.GameController;
import chess.controller.RoomController;
import chess.controller.UserController;
import chess.repository.MoveDao;
import chess.repository.RoomDao;
import chess.repository.UserDao;
import chess.repository.jdbc.JdbcMoveDao;
import chess.repository.jdbc.JdbcRoomDao;
import chess.repository.jdbc.JdbcUserDao;
import chess.service.GameService;
import chess.service.RoomService;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        UserDao userDao = new JdbcUserDao();
        RoomDao roomDao = new JdbcRoomDao();
        MoveDao moveDao = new JdbcMoveDao();

        UserService userService = new UserService(userDao);
        RoomService roomService = new RoomService(roomDao);
        GameService gameService = new GameService(roomDao, moveDao);

        GameController gameController = new GameController(inputView, outputView, gameService);
        RoomController roomController = new RoomController(inputView, outputView, roomService, gameController);
        UserController userController = new UserController(inputView, outputView, userService, roomController);

        userController.login();

        scanner.close();
    }
}
