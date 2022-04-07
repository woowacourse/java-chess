package chess.router;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.WebController;
import chess.dto.response.CreateGameDto;
import chess.dto.response.SearchResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebRouter {

    private static final String HOME_ROUTE = "/";
    private static final String SEARCH_ROUTE = "/search";
    private static final String GAME_ROUTE = "/game/:id";
    private static final String RESULT_ROUTE = "/result/:id";
    private static final String JSON_CONTENT_TYPE = "application/json";

    private final WebController controller = new WebController();

    public void initHomeRouteHandler() {
        get(HOME_ROUTE, (req, res) -> render(controller.countGames(), "home.html"));
        post(HOME_ROUTE, (req, res) -> {
            res.type(JSON_CONTENT_TYPE);
            CreateGameDto gameCreated = controller.initGame();
            return gameCreated.toJson();
        });
    }

    public void initSearchRouteHandler() {
        get(SEARCH_ROUTE, (req, res) -> render(controller.countGames(), "search.html"));
        post(SEARCH_ROUTE, (req, res) -> {
            res.type(JSON_CONTENT_TYPE);
            SearchResultDto searchResult = controller.searchGame(req);
            return searchResult.toJson();
        });
    }

    public void initGameRouterHandler() {
        get(GAME_ROUTE, (req, res) -> render(controller.findGame(req), "game.html"));
        post(GAME_ROUTE, (req, res) -> render(controller.playGame(req), "game.html"));
    }

    public void initResultRouterHandler() {
        get(RESULT_ROUTE, (req, res) -> render(controller.findGameResult(req), "result.html"));
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
