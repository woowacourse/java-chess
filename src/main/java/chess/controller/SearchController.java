package chess.controller;

import static chess.web.WebUtils.render;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.SearchResultDto;
import chess.service.ChessService;
import spark.Request;

public class SearchController {

    private static final String SEARCH_ROUTE = "/search";
    private static final String SEARCH_QUERY_PARAMETER = "game_id";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String HTML_TEMPLATE_PATH = "search.html";

    private final ChessService chessService = ChessService.getInstance();

    public void initRouteHandler() {
        get(SEARCH_ROUTE, (req, res) -> render(chessService.countGames(), HTML_TEMPLATE_PATH));
        post(SEARCH_ROUTE, (req, res) -> {
            res.type(JSON_CONTENT_TYPE);
            return searchGame(req).toJson();
        });
    }

    private SearchResultDto searchGame(Request request) {
        int gameId = parseInt(request.queryParams(SEARCH_QUERY_PARAMETER));
        return chessService.searchGame(gameId);
    }
}
