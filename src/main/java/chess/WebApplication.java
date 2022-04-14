package chess;

import static spark.Spark.*;

import chess.controller.GameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        final GameController controller = new GameController();

        get("/", controller::init, new HandlebarsTemplateEngine());

        get("/start", controller::start, new HandlebarsTemplateEngine());

        get("/chess", controller::printBoard, new HandlebarsTemplateEngine());

        post("/move", controller::move);

        get("/status", controller::status, new HandlebarsTemplateEngine());

        get("/result", controller::result, new HandlebarsTemplateEngine());

        get("/restart", controller::restart, new HandlebarsTemplateEngine());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
