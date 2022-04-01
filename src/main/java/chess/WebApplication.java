package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.controller.WebController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final WebController controller = new WebController();

    public static void main(String[] args) {
        staticFiles.location("/static");
        get("/", (req, res) -> render(null, "home.html"));
        get("/new-game", (req, res) -> render(controller.initGame(), "new-game.html"));
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
