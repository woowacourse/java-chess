package chess.controller;

import static chess.web.WebUtils.render;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.command.MoveRoute;
import chess.domain.event.MoveEvent;
import chess.dto.GameDto;
import chess.service.ChessService;
import spark.Request;

public class GameController {

    private static final String GAME_ROUTE = "/game/:id";
    private static final String GAME_ID_PARAMETER = "id";
    private static final String HTML_TEMPLATE_PATH = "game.html";

    private final ChessService chessService = ChessService.getInstance();

    public void initRouteHandler() {
        get(GAME_ROUTE, (req, res) -> render(findGame(req), HTML_TEMPLATE_PATH));
        post(GAME_ROUTE, (req, res) -> render(playGame(req), HTML_TEMPLATE_PATH));
    }

    private GameDto findGame(Request request) {
        int gameId = parseInt(request.params(GAME_ID_PARAMETER));
        return chessService.findGame(gameId);
    }

    private GameDto playGame(Request request) {
        int gameId = parseInt(request.params(GAME_ID_PARAMETER));
        String body = request.body();
        MoveRoute moveRoute = MoveRoute.ofJson(body);

        return chessService.playGame(gameId, new MoveEvent(moveRoute));
    }
}
