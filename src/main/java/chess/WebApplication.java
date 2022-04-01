package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.WebController;
import chess.model.NewGameModel;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final WebController controller = new WebController();

    public static void main(String[] args) {
        staticFiles.location("/static");
        get("/", (req, res) -> render(null, "home.html"));
        post("/new-game", (req, res) -> {
            res.type("application/json");
            NewGameModel newGameModel = controller.initGame();
            return newGameModel.toJson();
        });
        get("/game/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params("id"));
            return render(controller.findGame(gameId), "game.html");
        });
        get("/search", (req, res) -> "TBA");
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
