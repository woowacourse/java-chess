package chess;

import static spark.Spark.exception;
import static spark.Spark.staticFiles;

import chess.controller.web.GameController;
import chess.controller.web.HomeController;
import chess.controller.web.PieceController;
import chess.controller.web.UserController;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.dao.UserDao;
import chess.dao.exception.UncheckedSQLException;
import chess.service.GameService;
import chess.service.PieceService;
import chess.service.UserService;
import chess.service.exception.DataNotFoundException;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        final UserService userService = new UserService(new UserDao());
        final GameService gameService = new GameService(new GameDao());
        final PieceService pieceService = new PieceService(new PieceDao());
        makeControllers(userService, gameService, pieceService);

        mapException();
    }

    private static void makeControllers(final UserService userService,
        final GameService gameService, final PieceService pieceService) {

        final HomeController homeController = new HomeController();
        final GameController gameController =
            new GameController(gameService, userService, pieceService);
        final UserController userController = new UserController(userService);
        final PieceController pieceController = new PieceController(pieceService);
    }

    private static void mapException() {
        exception(DataNotFoundException.class, (exception, request, response) -> {
            response.status(500);
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
