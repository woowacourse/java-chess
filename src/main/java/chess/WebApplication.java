package chess;

import chess.controller.web.GameController;
import chess.controller.web.RoomController;
import chess.service.dao.DBConfig;
import chess.view.web.OutputView;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        final DBConfig dbConfig = new DBConfig();
        final Connection connection = dbConfig.getConnection();

        final GameController gameController = new GameController(connection);
        final RoomController roomController = new RoomController(connection);

        gameController.mapping();
        roomController.mapping();

        handlingSQLException();
        handlingPageNotFoundError();
    }

    private static void handlingSQLException() {
        exception(SQLException.class, (e, request, response) -> {
            response.status(404);
            response.body("error : " + e.getMessage());
        });
    }

    private static void handlingPageNotFoundError() {
        get("*", (req, res) -> {
            if (!req.pathInfo().startsWith("/static")) {
                res.status(404);
                return OutputView.printErrorMessage("Page not found");
            }
            return null;
        });
    }
}
