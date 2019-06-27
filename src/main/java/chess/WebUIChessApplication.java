package chess;

import chess.controller.MoveController;
import chess.controller.ScoreController;
import chess.controller.StartController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        StartController startController = StartController.getInstance();
        MoveController moveController = MoveController.getInstance();
        ScoreController scoreController = ScoreController.getInstance();

        staticFileLocation("/static");
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            return render(map, "index.html");
        });

        get(StartController.PATH, startController::start);

        get(MoveController.PATH, moveController::move);

        get(ScoreController.PATH, scoreController::score);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
