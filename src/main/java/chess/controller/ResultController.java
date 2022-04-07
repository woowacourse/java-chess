package chess.controller;

import static chess.web.WebUtils.render;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;

import chess.dto.GameResultDto;
import chess.service.ChessService;
import spark.Request;

public class ResultController {

    private static final String RESULT_ROUTE = "/result/:id";
    private static final String RESULT_TARGET_GAME_PARAMETER = "id";
    private static final String HTML_TEMPLATE_PATH = "result.html";

    private final ChessService chessService = ChessService.getInstance();

    public void initRouteHandler() {
        get(RESULT_ROUTE, (req, res) -> render(findGameResult(req), HTML_TEMPLATE_PATH));
    }

    public GameResultDto findGameResult(Request request) {
        int gameId = parseInt(request.params(RESULT_TARGET_GAME_PARAMETER));
        return chessService.findGameResult(gameId);
    }
}
