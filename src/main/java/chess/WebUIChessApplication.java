package chess;

import chess.controller.ChessGameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/new", ChessGameController.CREATE_NEW_GAME);
        get("/latest", ChessGameController.CREATE_LATEST_GAME);

        get("/movable", ChessGameController.CREATE_MOVABLE_POSITIONS);
        post("/move", ChessGameController.MOVE_PIECES);
        get("/score", ChessGameController.CALCULATE_SCORE);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
