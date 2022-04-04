package chess.router;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.WebController;
import chess.dto.response.SearchResultDto;
import chess.dto.request.PlayGameRequestDto;
import chess.dto.response.CreateGameDto;
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
            int gameId = Integer.parseInt(req.queryParams("game_id"));
            SearchResultDto searchResult = controller.searchGame(gameId);
            return searchResult.toJson();
        });
    }

    public void initGameRouterHandler() {
        get(GAME_ROUTE, (req, res) -> {
            int gameId = Integer.parseInt(req.params("id")); // TODO: handle exception on non int input
            return render(controller.findGame(gameId), "game.html");
        });
        post(GAME_ROUTE, (req, res) -> {
            PlayGameRequestDto request = new PlayGameRequestDto(req.params("id"), req.body());
            return render(controller.playGame(request), "game.html");
        });
    }

    public void initResultRouterHandler() {
        get(RESULT_ROUTE, (req, res) -> {
            int gameId = Integer.parseInt(req.params("id"));
            return render(controller.findGameResult(gameId), "result.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
