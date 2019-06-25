package chess;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Game game = new Game();

            int from = Integer.parseInt(req.queryParams("from"));
            int to = Integer.parseInt(req.queryParams("to"));

            return game.play(from, to).toString();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
