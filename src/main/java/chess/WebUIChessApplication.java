package chess;

import chess.controller.WebController;
import chess.domain.dao.CommandDao;
import chess.domain.dao.HistoryDao;
import chess.service.ChessService;

import java.sql.SQLException;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        final WebController webController = initWebController(new CommandDao(), new HistoryDao());

        get("/play", webController::moveToMainPage);
        get("/play/:name/new", webController::playNewGame);
        post("/play/move", webController::movePiece);
        get("/play/continue", webController::continueGame);
        get("/play/end", webController::endGame);
    }

    private static WebController initWebController(CommandDao commandDao, HistoryDao historyDao) {
        return new WebController(new ChessService(commandDao, historyDao));
    }
}
