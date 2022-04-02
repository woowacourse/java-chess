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

    private final WebController controller = new WebController();

    public void initHomeRouteHandler() {
        get("/", (req, res) -> render(controller.countGames(), "home.html"));
        post("/", (req, res) -> {
            res.type("application/json");
            CreateGameDto gameCreated = controller.initGame();
            return gameCreated.toJson();
        });
    }

    public void initSearchRouteHandler() {
        get("/search", (req, res) -> render(controller.countGames(), "search.html"));
        post("/search", (req, res) -> {
            res.type("application/json");
            int gameId = Integer.parseInt(req.queryParams("game_id"));
            SearchResultDto searchResult = controller.searchGame(gameId);
            return searchResult.toJson();
        });
    }

    public void initGameRouterHandler() {
        get("/game/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params("id")); // TODO: handle exception on non int input
            return render(controller.findGame(gameId), "game.html");
        });
        post("/game/:id", (req, res) -> {
            PlayGameRequestDto request = new PlayGameRequestDto(req.params("id"), req.body());
            return render(controller.playGame(request), "game.html");
        });
    }

    public void initResultRouterHandler() {
        get("/result/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params("id"));
            return render(controller.findGameResult(gameId), "result.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
