package chess.controller;

import static chess.web.WebUtils.render;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dto.SearchResultDto;
import chess.dto.SuccessResponseDto;
import chess.service.ChessService;
import chess.web.WebUtils;
import spark.Request;

public class SearchController {

    private static final String SEARCH_ROUTE = "/search";
    private static final String SEARCH_QUERY_PARAMETER = "game_id";
    private static final String HTML_TEMPLATE_PATH = "search.html";

    private final ChessService chessService = ChessService.getInstance();

    public void initRouteHandler() {
        get(SEARCH_ROUTE, (req, res) -> render(chessService.countGames(), HTML_TEMPLATE_PATH));
        post(SEARCH_ROUTE, (req, res) -> {
            res.type(WebUtils.JSON_CONTENT_TYPE);
            return new SuccessResponseDto(getSearchGameResponse(req)).toJson();
        });
    }

    private String getSearchGameResponse(Request request) {
        int gameId = parseInt(request.queryParams(SEARCH_QUERY_PARAMETER));
        SearchResultDto searchResult = chessService.searchGame(gameId);
        return searchResult.toJson();
    }
}
