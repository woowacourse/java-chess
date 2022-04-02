package chess.router;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.WebController;
import chess.dto.response.PlayGameRequestDto;
import chess.model.NewGameModel;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebRouter {

    private final WebController controller = new WebController();

    public void initHomeRouteHandler() {
        get("/", (req, res) -> render(null, "home.html"));
        post("/", (req, res) -> {
            res.type("application/json");
            NewGameModel newGameModel = controller.initGame();
            return newGameModel.toJson();
        });
    }

    public void initSearchRouteHandler() {
        get("/search", (req, res) -> render(null, "search.html"));
        post("/search", (req, res) -> {
            res.type("application/json");
            int gameId = Integer.parseInt(req.queryParams("game_id"));
            return controller.searchGame(gameId).toJson();
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

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
