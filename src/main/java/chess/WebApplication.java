package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.WebController;
import chess.dto.PlayGameRequestDto;
import chess.model.NewGameModel;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final WebController controller = new WebController();

    public static void main(String[] args) {
        staticFiles.location("/static");
        initHomeRouteHandler();
        initSearchRouteHandler();
        initGameRouterHandler();
    }

    private static void initHomeRouteHandler() {
        get("/", (req, res) -> render(null, "home.html"));
        post("/", (req, res) -> {
            res.type("application/json");
            NewGameModel newGameModel = controller.initGame();
            return newGameModel.toJson();
        });
    }

    private static void initSearchRouteHandler() {
        get("/search", (req, res) -> render(null, "search.html"));
        post("/search", (req, res) -> {
            res.type("application/json");
            int gameId = Integer.parseInt(req.queryParams("game_id"));
            return controller.searchGame(gameId).toJson();
        });
    }

    private static void initGameRouterHandler() {
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
