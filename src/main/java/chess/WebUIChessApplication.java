package chess;

import static spark.Spark.exception;
import static spark.Spark.staticFiles;

import chess.controller.web.GameController;
import chess.controller.web.HomeController;
import chess.controller.web.UserController;
import chess.dao.GameDao;
import chess.dao.UserDao;
import chess.dao.exception.UncheckedSQLException;
import chess.service.GameService;
import chess.service.UserService;
import chess.service.exception.DataNotFoundException;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        final UserService userService = new UserService(new UserDao());
        final GameService gameService = new GameService(new GameDao());
        makeControllers(userService, gameService);

        mapException();
    }

    private static void makeControllers(final UserService userService,
        final GameService gameService) {
        final HomeController homeController = new HomeController();
        final GameController gameController = new GameController(gameService, userService);
        final UserController userController = new UserController(userService);
    }

    private static void mapException() {
        exception(DataNotFoundException.class, (exception, request, response) -> {
            response.status(200);
            response.type("application/json");
            response.body("{}");
        });

        exception(UncheckedSQLException.class, (exception, request, response) -> {
            response.status(500);
            response.type("text/html");
            response.body(exception.getMessage());
        });
    }
}
