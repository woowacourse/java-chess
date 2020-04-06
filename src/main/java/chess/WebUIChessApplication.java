package chess;

import static chess.util.JsonUtil.*;
import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.service.ChessService;
import chess.service.JdbcChessService;
import chess.util.RoutesUtil;

public class WebUIChessApplication {
    private static ChessService service = new JdbcChessService();
    private static WebChessController controller = new WebChessController(service);

    public static void main(String[] args) {
        RoutesUtil.addTemporaryPlayers();
        RoutesUtil.configure();
        options("/*", RoutesUtil::handleCors);
        before(RoutesUtil::beforeEach);

        get("/", controller::renderEntryPoint);
        get("/boards", controller::getPlayerContexts, json());
        post("/boards", controller::addGameAndGetPlayers, json());

        get("/boards/:id", controller::getBoard, json());
        post("/boards/:id", controller::resetBoard, json());
        delete("/boards/:id", controller::finishGame, json());

        path("/boards/:id", () -> {
            get("/status", controller::isGameOver, json());
            get("/turn", controller::isWhiteTurn, json());
            get("/score", controller::getScore, json());

            post("/move", controller::move, json());
            post("/movable", controller::findAllAvailablePath, json());
        });

        get("/scores", controller::getScoreContexts, json());
    }
}
