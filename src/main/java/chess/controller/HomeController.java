package chess.controller;

import chess.service.BoardService;
import chess.service.GameService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static chess.controller.Utils.render;

public class HomeController {
    public static final String URL = "/";

    private static final HomeController INSTANCE = new HomeController();

    private HomeController() {
    }

    public static HomeController getInstance() {
        return INSTANCE;
    }

    public String getGameRooms(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        GameService gameService = GameService.getInstance();
        model.put("gameRooms", gameService.getAllGames());
        return render(model, "home.html");
    }

    public String setUpGame(final Request req, final Response res) {
        String gameName = req.queryParams("gameName");
        GameService gameService = GameService.getInstance();
        gameService.addGame(gameName);
        BoardService boardService = BoardService.getInstance();
        boardService.initializeBoard(gameName);
        res.redirect("/game/" + gameService.getGameId(gameName));
        return null;
    }
}
