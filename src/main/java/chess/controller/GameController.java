package chess.controller;

import chess.model.GameFlow;
import chess.service.EngineService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static chess.controller.Utils.render;

public class GameController {
    public static final String URL = "/game/:gameId";

    private static final GameController INSTANCE = new GameController();

    private GameController() {
    }

    public static GameController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req, final Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("gameId", req.params("gameId"));
        return render(model, "game.html");
    }

    public String post(final Request req, final Response res) {
        EngineService engineService = EngineService.getInstance();
        GameFlow gameFlow = engineService.validateMove(req.session().attribute("engine"), req.queryParams("source"), req.queryParams("target"));
        if (gameFlow == GameFlow.CONTINUE) {
            engineService.updatePieces(req.queryParams("source"), req.queryParams("target"), req.session().attribute("engine"), req.params("gameId"));
        }
        return gameFlow.name();
    }
}
