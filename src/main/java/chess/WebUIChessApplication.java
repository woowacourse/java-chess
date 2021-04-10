package chess;

import static spark.Spark.exception;
import static spark.Spark.staticFiles;

import chess.controller.web.GameController;
import chess.controller.web.HomeController;
import chess.controller.web.UserController;
import chess.dao.exception.UncheckedSQLException;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        final HomeController homeController = new HomeController();
        final GameController gameController = new GameController();
        final UserController userController = new UserController();

        exception(RuntimeException.class, ((exception, request, response) -> {
            response.status(500);
            response.type("text/html");
            response.body(exception.getMessage());
        }));
    }
}
