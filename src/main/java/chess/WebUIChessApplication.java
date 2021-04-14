package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import chess.controller.WebController;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {


    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public/assets");

        WebController webController = new WebController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lobby.html");
        });

        get("/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/lobby/new", webController::newGame);

        get("/chessboard/:id", webController::loadGame);

        put("/:id/move", "application/json", webController::move);

        get("/:id/turn", webController::turn);

        get("/:id/result", webController::result);

        post("/:id/finish", webController::finish);

        get("/:id/finish", webController::finished);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
