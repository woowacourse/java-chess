package chess.controller;

import static chess.web.WebUtils.render;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.CreateGameDto;
import chess.dto.SuccessResponseDto;
import chess.service.ChessService;
import chess.web.WebUtils;

public class HomeController {

    private static final String HOME_ROUTE = "/";
    private static final String HTML_TEMPLATE_PATH = "home.html";

    private final ChessService chessService = ChessService.getInstance();

    public void initRouteHandler() {
        get(HOME_ROUTE, (req, res) -> render(chessService.countGames(), HTML_TEMPLATE_PATH));
        post(HOME_ROUTE, (req, res) -> {
            res.type(WebUtils.JSON_CONTENT_TYPE);
            return new SuccessResponseDto(getGameCreatedResponse()).toJson();
        });
    }

    private String getGameCreatedResponse() {
        CreateGameDto gameCreated = chessService.initGame();
        return gameCreated.toJson();
    }
}
